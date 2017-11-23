package jdbc.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mchange.v2.c3p0.impl.DbAuth;
import com.neuedu.util.DBUtil;

public class SqlInject {

	public static void main(String[] args) {
		//batchExes();
		//updateUser();
		deleteUser();
	}
	public static void deleteUser() {
		Connection conn=null;
		Statement stmt=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			
			stmt.executeQuery("delete u_user  where name='李四'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(stmt, conn);
		}
	}
	public static void updateUser() {
		Connection conn=null;
		Statement stmt=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			
			stmt.executeQuery("update u_user set birthday=sysdate where name='张三'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(stmt, conn);
		}
		
	}
	public static void batchExes() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			//插入t_user表
			stmt.addBatch("insert into u_user values('张三',888888,'zhangsan@126.com',to_date('1986-10-11','yyyy-mm-dd'))");
			stmt.addBatch("insert into u_user values('李四',999999,'lisi@126.com',to_date('1988-10-23','yyyy-mm-dd'))");
			stmt.addBatch("insert into u_user values('王五',777777,'wangwu@126.com',to_date('1990-9-11','yyyy-mm-dd'))");
			stmt.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn);
		}
	}
	

}
