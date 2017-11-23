package jdbc.demo1.pool;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
	//初始化一个实例
	private static final ConnectionPool pool=new ConnectionPool();
	//获取c3p0连接池对象
	private static final ComboPooledDataSource ds=new ComboPooledDataSource();
	//设置私有构造器
	private ConnectionPool() {
		
	}
	//对外提供获取这个实例的方法
	public static ConnectionPool getInstance() {
		return pool;
	}
	public Connection getDBConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void close(Statement stmt,Connection conn) {
		close(null,stmt,conn);
	}
	public void close(ResultSet rs,Statement stmt,Connection conn) {
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

}
