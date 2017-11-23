package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.UserTypeDao;
import com.neuedu.domain.CertType;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;
import com.neuedu.util.ResultSetCallBack;

public class UserTypeDaoImpl implements UserTypeDao {
	
	private Connection conn=null;
	
	public UserTypeDaoImpl(Connection conn) {
		this.conn=conn;
	}

	@Override
	public List<UserType> queryUserType() throws Exception {
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<UserType> list=new ArrayList<UserType>();
		try {
			stmt=conn.prepareStatement("select id, content from tab_usertype");
			rs=stmt.executeQuery();
			while(rs.next()) {
				UserType uType=new UserType(rs.getInt("ID"),rs.getString("CONTENT"));
				list.add(uType);
			}
		} finally {
			DBUtil.close(rs, stmt);
		}
		return list;
	}

}
