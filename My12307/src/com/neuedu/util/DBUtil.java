package com.neuedu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.google.gson.Gson;




public class DBUtil {
	
	private static final Logger log=Logger.getLogger(DBUtil.class);
	//获取c3p0连接池
	//private static final ComboPooledDataSource ds=new ComboPooledDataSource();
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConn() {
		try {
			//初始化上下文
			Context ctx=new InitialContext();
			//根据名称搜索对应的服务
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/12306");
			return ds.getConnection();
		} catch (Exception e) {
			log.error("获取数据库连接失败",e);
		}
		return null;
	}
	
	/**
	 * 根据传入的数据库连接设置为非自动提交
	 * @param conn
	 */
	public static void beginTransaction(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行事务提交，同时把数据库连接设置为默认的 自动提交
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 执行事务回滚
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs,Statement stmt) {
		close(null,rs,stmt);
	}
	
	public static void close(Statement stmt) {
		close(null,null,stmt);
	}
	
	public static void close(Connection conn) {
		close(conn,null,null);
	}
	
	public static void close(Connection conn,Statement stmt) {
		close(conn,null,stmt);
	}
	/**
	 * 释放数据库资源
	 * @param conn
	 * @param rs
	 * @param stmt
	 */
	public static void close(Connection conn,ResultSet rs,Statement stmt) {
		if(null!=rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 通过该方法获取SQL语句中的第一行第一列的整数值
	 * 一般用于类似于select count(1) from tab这样的情况
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int queryForInt(Connection conn,String sql,Object[] params) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int cnt=-1;
		try {
			log.info("queryForInt-sql="+sql+"[参数为："+params+"}");
			stmt=conn.prepareStatement(sql);
			if(null!=params&&params.length>0){
				for(int i=0;i<params.length;i++){
					stmt.setObject((i+1), params[i]);
				}
			}
			rs=stmt.executeQuery();
			if(rs.next()){
				//取SQL结果集中的第一行第一列的值
				cnt=rs.getInt(1);
			}
		} finally {
			DBUtil.close(rs,stmt);
		}
		return cnt;
	}
	
	public static int queryForInt(Connection conn,String sql) throws SQLException {
		return queryForInt(conn, sql,null);
	}
	
	/**
	 * 通过该方法获取SQL语句中的第一行第一列的字符串值
	 * 一般用于类似于select name from tab where id=100这样的情况
	 * 其中ID是唯一的
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static String queryForString(Connection conn,String sql,Object[] params) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String res="-1";
		try {
			log.info("queryForString-sql="+sql+" 参数="+new Gson().toJson(params));
			stmt=conn.prepareStatement(sql);
			if(null!=params&&params.length>0) {
				for(int i=0;i<params.length;i++) {
					stmt.setObject((i+1),params[i]);
				}
			}
			rs=stmt.executeQuery();
			if(rs.next()) {
				//取SQL结果集中的第一行第一列的值
				res=rs.getString(1);
			}
		} finally {
			DBUtil.close(rs, stmt);
		}
		return res;
	}
	
	public static String queryForString(Connection conn,String sql) throws SQLException {
		return queryForString(conn, sql,null);
	}
	
	/**
	 * 通过指定回调函数让调用者定制化数据的输出
	 * @param conn
	 * @param sql
	 * @param params
	 * @param call
	 * @return
	 * @throws SQLException
	 */
	public static <T>T queryByCallBack(Connection conn,String sql,Object[] params,ResultSetCallBack<T> call)throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		T res=null;
		try {
			log.info("queryByCallBack-sql="+sql+" 参数="+new Gson().toJson(params));
			stmt=conn.prepareStatement(sql);
			if(null!=params&&params.length>0) {
				for(int i=0;i<params.length;i++) {
					stmt.setObject((i+1), params[i]);
				}
			}
			rs=stmt.executeQuery();
			res=call.parseResultSet(rs);
		} finally {
			DBUtil.close(rs, stmt);
		}
		return res;
	}
	
	public static <T>T queryByCallBack(Connection conn,String sql,ResultSetCallBack<T> call)throws SQLException{
		return queryByCallBack(conn, sql, null, call);
	}
	
	/**
	 * 用于不需要返回结果集的SQL
	 * 类似delete from tab\ update table\truncate table
	 * create table\drop table
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int execSQL(Connection conn,String sql,Object[] params)throws SQLException{
		PreparedStatement stmt=null;
		int cnt=-1;
		try {
			log.info("execSQL-sql="+sql+" 参数="+new Gson().toJson(params));
			stmt=conn.prepareStatement(sql);
			if(null!=params&&params.length>0) {
				for(int i=0;i<params.length;i++) {
					stmt.setObject((i+1), params[i]);
				}
			}
			cnt=stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
		}
		return cnt;
	}
	public static int execSQL(Connection conn,String sql)throws SQLException{
		return execSQL(conn, sql, null);
	}
	
	public static <T>T queryByCallBackPage(Connection conn,
			String sql,Object[] params,PageBean page,ResultSetCallBack<T> call) throws SQLException{
		int start=page.getStartRowNum();
		int end=page.getEndRowNum();
		String pageSQL="select * from (select rownum rn,b.* from ("+sql+") b where rownum<="+end+") where rn>="+start+"";
		log.info("pageSQL="+pageSQL);
		return queryByCallBack(conn,pageSQL,params,call);
	}
	
	public static <T>T queryByCallBackPage(Connection conn,
			String sql,PageBean page,ResultSetCallBack<T> call) throws SQLException{
		return queryByCallBackPage(conn,sql,null,page,call);
	}

	

}
