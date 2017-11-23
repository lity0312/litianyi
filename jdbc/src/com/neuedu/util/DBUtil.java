package com.neuedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static {
		//静态块，类被加载时自动执行一次。
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:SCOTT";
		Connection conn=null;
		
			 conn=DriverManager.getConnection(url,"scott","scott");
	
		return conn;
	}
	public static void close(Statement stmt,Connection conn) {
		close(null,stmt,conn);
	}
	public static void close(ResultSet rs,Statement stmt,Connection conn) {
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
