package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.UserTypeDaoImpl;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;

public class UserTypeService extends BaseService{
	
	private static final UserTypeService instance=new UserTypeService();
	public UserTypeService() {}
	public static UserTypeService getInstance() {
		return instance;
	}
	public List<UserType> queryUserType(){
		Connection conn=null;
		List<UserType>res=new ArrayList<>();
		try {
			conn=DBUtil.getConn();
			UserTypeDaoImpl dao=new UserTypeDaoImpl(conn);
			res=dao.queryUserType();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return res;
	}

}
