package com.neuedu.business.dao;

import java.sql.Connection;
import java.util.List;

import com.neuedu.domain.UserType;

public interface UserTypeDao {
	
	
	/**
	 * 获取所有用户类型
	 * @return
	 */	
	public List<UserType> queryUserType()throws Exception;

}
