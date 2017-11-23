package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.ProvinceDaoImpl;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class ProvinceService extends BaseService{
	
	private static final ProvinceService instance=new ProvinceService();
	private ProvinceService() {}
	public static ProvinceService getInstance() {
		return instance;
	}
	
	public List<Province> queryProvince(){
		Connection conn=null;
		List<Province> list=new ArrayList<Province>();
		try {
			conn=DBUtil.getConn();
			ProvinceDaoImpl dao=new ProvinceDaoImpl(conn);
			list=dao.queryProvince();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
}
