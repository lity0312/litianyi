package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.CityDao;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class CityDaoImpl implements CityDao {
	
	private Connection conn=null;
	public CityDaoImpl(Connection conn) {
		this.conn=conn;
	}
	
	public List<City> queryCity(String where) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<City> list=new ArrayList<City>();
		try {
			StringBuffer sb=new StringBuffer();
			sb.append("select a.*,b.id p_id,b.provinceid,b.province from tab_city a,tab_province b where a.father=b.provinceid");
			if(null!=where&&!"".equals(where)) {
				sb.append(where); 
			}
			System.out.println(sb.toString());
			stmt=conn.prepareStatement(sb.toString());
			rs=stmt.executeQuery();
			while(rs.next()) {
				Province pro=new Province(rs.getInt("P_ID"), rs.getString("PROVINCEID"), rs.getString("PROVINCE"));
				City city=new City(rs.getInt("ID"),rs.getString("CITYID"),rs.getString("CITY"),pro);
			    list.add(city);
			}
		} finally {
			DBUtil.close(rs, stmt);
		}
		return list;
	}

	@Override
	public List<City> queryCity() throws Exception{
		
		return queryCity("");
	}

	@Override
	public List<City> queryCityByProvince(Province p) throws Exception{
		String where=" and b.province='"+p.getProvince()+"'";
		return queryCity(where);
	}

	@Override
	public List<City> queryCityByID(String id) throws Exception{
		String where=" and b.provinceid='"+id+"'";
		return queryCity(where);
	}

	@Override
	public String queryCityID(String name) throws Exception {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.prepareStatement("select cityid from tab_city where city like ?");
			stmt.setString(1, "%"+name+"%");
			rs=stmt.executeQuery();
			String cityId="";
			if(rs.next()) {
				cityId=rs.getString("CITYID");
			}
			return cityId;
		}finally {
			DBUtil.close(rs, stmt);
		}
		
	}

}
