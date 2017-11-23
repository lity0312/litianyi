package jdbc.demo1;

import java.sql.*;
public class JdbcDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testJdbc();
		addUser();

	}
	
		public static void testJdbc() {
			try {
				//加载JDBC驱动程序
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//建立到数据库的连接
				String url="jdbc:oracle:thin:@127.0.0.1:1521:SCOTT";
				Connection conn=DriverManager.getConnection(url,"scott","scott");
				//创建Stament对象
				Statement stmt=conn.createStatement();
				//执行SQL语句
				String sql="select empno,ename from emp";
				
				ResultSet set=stmt.executeQuery(sql);
				
				//System.out.println("编号\t姓名\t年龄\t生日");
				//循环遍历结果集，并从中取出数据库中的数据
				while(set.next()) {
					int empno=set.getInt("empno");
					String ename=set.getString("ename");
					

					System.out.println(empno+"\t"+ename);
				}
				//关闭资源(由小向大)
				set.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			public static void addUser() {
				try {
					//加载JDBC驱动程序
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//建立到数据库的连接
					String url="jdbc:oracle:thin:@127.0.0.1:1521:SCOTT";
					Connection conn=DriverManager.getConnection(url,"scott","scott");
					//创建Stament对象
					Statement stmt=conn.createStatement();
					//执行SQL语句
					String sql="insert into t_user values (t_user_sequen.nextval,'张三'，19,sysdate）";
					String sql1="insert into t_user values (t_user_sequen.nextval,'李四'，20,sysdate)";
					String sql2="insert into t_user values (t_user_sequen.nextval,'王五'，22,sysdate)";
					boolean set=stmt.execute(sql);
					boolean set1=stmt.execute(sql1);
					boolean set2=stmt.execute(sql2);
					//System.out.println("编号\t姓名\t年龄\t生日");
					//循环遍历结果集，并从中取出数据库中的数据
					
					//关闭资源(由小向大)
					
					stmt.close();
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
