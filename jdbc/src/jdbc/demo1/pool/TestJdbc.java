package jdbc.demo1.pool;

import java.sql.*;


public class TestJdbc {

	public static void main(String[] args) throws Exception {
		ConnectionPool pool=ConnectionPool.getInstance();
		Connection conn=pool.getDBConnection();
		//1.加载oracle驱动程序
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.定义连接URL
		//String url="jdbc:oracle:thin:@127.0.0.1:1521:SCOTT";
		//3.建立连接
		//Connection conn=DriverManager.getConnection(url, "scott", "scott");
		//4.创建statement对象
		PreparedStatement ps=conn.prepareStatement("select * from emp where empno=?");
		ps.setInt(1, 7369);
		//5.拼接SQL并执行
		//6.获取结果集，操作数据
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename"));
			
		}
		Thread.sleep(30*1000);
		//7.关闭资源
		pool.close(rs, ps, conn);

	}

}
