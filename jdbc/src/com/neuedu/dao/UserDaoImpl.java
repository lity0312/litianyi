package com.neuedu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.pojo.User;
import com.neuedu.util.DBUtil;

public class UserDaoImpl implements UserDao{
	public static void main(String[] args) {
		UserDaoImpl impl=new UserDaoImpl(); 
		User user=new User(0,"tom",0,new Date());
		List<User> list=impl.queryUser(user);
		for(User user2:list) {
			System.out.println(user2.toString());
		}
		/*UserDaoImpl impl=new UserDaoImpl();
		int id=24;
		User user=impl.getUserbyId(id);
		System.out.println(user);*/
		/*UserDaoImpl impl=new UserDaoImpl();
		int id=24;
		impl.getUserbyId(id);
		impl.removeUser(id);
*/		/*UserDaoImpl impl=new UserDaoImpl();
		User user=new User(22,"tom2",122,new Date());
		impl.updateUser(user);*/
		/*UserDaoImpl impl=new UserDaoImpl();
		User user=new User(0,"tom",12,new Date());
		impl.addUser(user);
		SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt=sft.parse("1986-01-02");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user=new User(0,"jack",22,dt);
		impl.addUser(user);*/
	}

	public boolean addUser(User user) {
		Connection conn=null;
		Statement stmt=null;
		boolean rs=true;
		try{
		 conn=DBUtil.getConnection();
		 stmt=conn.createStatement();
		StringBuffer sb=new StringBuffer();
		sb.append("insert into t_user values(t_user_seq.nextval,");
		sb.append("'");
		sb.append(user.getName());
		sb.append("'");
		sb.append(",").append(user.getAge()).append(",");
		SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
		String dt=sft.format(user.getBirthday());
		sb.append("to_date('"+dt+"','yyyy-mm-dd')");
		sb.append(")");
		stmt.execute(sb.toString());
		}catch(SQLException e) {
			rs=false;
			e.printStackTrace();
		}finally {
			DBUtil.close(stmt, conn);
		}
		return rs;
	}

	@Override
	public boolean updateUser(User user) {
		Connection conn=null;
		Statement stmt=null;
		boolean result=true;
		try {
			 conn=DBUtil.getConnection();
			 stmt=conn.createStatement();
			StringBuffer sb=new StringBuffer();
			sb.append("update t_user set name='");
			sb.append(user.getName()).append("',");
			sb.append("age=").append(user.getAge()).append(",");
			Date dt=user.getBirthday();
			SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
			String dtStr=sft.format(dt);
			sb.append("birthday=to_date('"+dtStr+"','yyyy-mm-dd')");
			sb.append(" where id=").append(user.getId());
			System.out.println("SQL="+sb.toString());
			int cnt=stmt.executeUpdate(sb.toString());
			if(cnt>0) {
				System.out.println("更新成功...");
			}else {
				result=false;
				System.out.println("更新失败...");
			}
		} catch (SQLException e) {
			result=false;
			e.printStackTrace();
		}finally {
			DBUtil.close(stmt, conn);
		}
		return result;
	}
 
	@Override
	public boolean removeUser(int id) {
		Connection conn=null;
		Statement stmt=null;
		boolean result=true;
		try {
			 conn=DBUtil.getConnection();
			 stmt=conn.createStatement();
			StringBuffer sb=new StringBuffer();
			sb.append("delete t_user where id=");
			sb.append(id);
			System.out.println("SQL="+sb.toString());
			int cnt=stmt.executeUpdate(sb.toString());
			if(cnt>0) {
				System.out.println("更新成功...");
			}else {
				result=false;
				System.out.println("更新失败...");
			}
		} catch (SQLException e) {
			result=false;
			e.printStackTrace();
		}finally {
			DBUtil.close(stmt, conn);
		}
		return result;	}

	@Override
	public User getUserbyId(int id) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			 conn=DBUtil.getConnection();
			 stmt=conn.createStatement();
			StringBuffer sb=new StringBuffer();
			sb.append("select * from t_user where id=").append(id);
			rs=stmt.executeQuery(sb.toString());
			if(rs.next()) {
				user=new User(rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("age"),rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			//result=false;
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.close(stmt, conn);
		}
		return user;
	}

	@Override
	public List<User> queryUser(User user) {
		List<User> userList=new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			 conn=DBUtil.getConnection();
			 stmt=conn.createStatement();
			StringBuffer sb=new StringBuffer();
			sb.append("select * from t_user where 1=1");
			if(null!=user.getName()&&!user.getName().equals("")) {
				sb.append(" and name='").append(user.getName()).append("'");
				
			}
			if(user.getAge()!=0) {
				sb.append(" and age=").append(user.getAge());
			}
			if(null!=user.getBirthday()) {
				SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd");
				String dt=dft.format(user.getBirthday());
				sb.append(" and birthday=to_date('"+dt+"','yyyy-mm-dd')");
			}
			System.out.println("SQL="+sb.toString());
			 rs=stmt.executeQuery(sb.toString());
			while(rs.next()) {
				userList.add(new User(rs.getInt("id"),
						rs.getString("name"),rs.getInt("age"),
						rs.getDate("birthday")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs,stmt, conn);
		}
				return userList;
	}

}
