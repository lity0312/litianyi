package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.ProvinceDao;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class ProvinceDaoImpl implements ProvinceDao {

	private Connection conn=null;
	public ProvinceDaoImpl(Connection conn) {
		this.conn=conn;
	}
	@Override
	public List<Province> queryProvince()throws Exception {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Province> list=new ArrayList<Province>();
		try {
			stmt=conn.prepareStatement("select * from tab_province");
			rs=stmt.executeQuery();
			while(rs.next()) {
				Province pro=new Province(rs.getInt("ID"),rs.getString("PROVINCEID"),rs.getString("PROVINCE"));
				list.add(pro);
			}
		} finally {
			DBUtil.close(rs, stmt);
		}
		return list;
	}

}
