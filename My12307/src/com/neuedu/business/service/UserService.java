package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.UserDaoImpl;
import com.neuedu.domain.User;
import com.neuedu.util.DBUtil;
import com.neuedu.util.PageBean;

public class UserService extends BaseService{
	
	//1.初始化实例
	private static final UserService instance=new UserService();
	
	//2.把该类的构造函数设置为私有
	private UserService() {}
	
	//3.需要提供一个静态方法获取这一单个实例
	public static UserService getInstance() {
		return instance;
	}
	
	public boolean addUser(User user) {
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		boolean saveFlag=true;
		try {
			saveFlag=userDao.addUser(user);
		} catch (Exception e) {
			saveFlag=false;
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return saveFlag;
	}
	public User login(String loginName,String pwd) {
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		User user=null;
		try {
			user=userDao.login(loginName,pwd);			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return user;
	}
	
	/**
	 * 判断指定的登录名是否存在
	 * @param loginName
	 * @return true:存在 false:不存在
	 */
	public boolean isLoginNameExists(String loginName) {
		Connection conn=null;
		boolean flag=false;
		try {
			conn=DBUtil.getConn();
			UserDaoImpl dao=new UserDaoImpl(conn);
			int cnt=dao.isLoginNameExists(loginName);
			if(cnt>0) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return flag;
	}
	
	/**
	 * 判断用户名是否存在，排除自身
	 * @param loginName
	 * @param userID
	 * @return
	 */
	public boolean isExistsForUpdate(String loginName,int userID) {
		boolean res=false;
		Connection conn=null;
		String sql="select count(1) from tab_user where username=? and id<>?";
		try {
			conn=DBUtil.getConn();
			int cnt=DBUtil.queryForInt(conn,sql, new Object[] {loginName,userID});
			if(cnt>0) {
				res=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return res;
	}
	
	public boolean changePwd(int userId,String newPwd,String oldPwd) {
		Connection conn=null;
		boolean execFlag=false;
		try {
			conn=DBUtil.getConn();
			UserDaoImpl dao=new UserDaoImpl(conn);
			int cnt=dao.ModifyPwd(userId, newPwd, oldPwd);
			if(cnt>0) {
				execFlag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return execFlag;
	}
	
	public List<User> queryUsers(User user){
		Connection conn=null;
		List<User>list=new ArrayList<>();
		try {
			conn=DBUtil.getConn();
			UserDaoImpl dao=new UserDaoImpl(conn);
			list=dao.queryUsers(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}

	public int updateUser(User user){
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		int cnt=-1;
		try {
			cnt=userDao.updateUser(user);
			//DBUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return cnt;
	}
	
	public List<User> queryUsers(User user,PageBean page){
		Connection conn=null;
		List<User>list=new ArrayList<>();
		try {
			conn=DBUtil.getConn();
			UserDaoImpl dao=new UserDaoImpl(conn);
			list=dao.queryUsers(user,page);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	/**
	 * 根据集合中的id删除用户信息（单个删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delUser(int id){
		int cnt=0;
		try {
//			cnt=dao.delUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	public int delUser(String[] ids){
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		int cnt=-1;
		try {
			cnt=userDao.delUser(ids);
		} catch (Exception e) {
			cnt=-1;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return cnt;
	}

	/**
	 * 根据用户userId查询单个用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User queryUserByID(int userId) {
		Connection conn=DBUtil.getConn();
		UserDaoImpl userDao=new UserDaoImpl(conn);
		User user=null;
		try {
			user=userDao.queryUserByID(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return user;
		
	}

	

}
