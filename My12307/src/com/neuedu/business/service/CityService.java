package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.CityDaoImpl;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.util.DBUtil;

public class CityService extends BaseService{
	
	public static final CityService instance=new CityService();
	private CityService() {}
	public static CityService getInstance() {
		return instance;
	}
	
	public String queryCityID(String name) {
		Connection conn=null;
		String cityId="-99";
		try {
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			cityId=dao.queryCityID(name);
			if("".equals(cityId)) {
				cityId="-99";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return cityId;
	}
	
	public List<City> queryCity(){
		Connection conn=null;
		List<City> list=new ArrayList<City>();
		try {
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			list=dao.queryCity();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	public List<City> queryCityByProvince(Province p){
		Connection conn=null;
		List<City> list=new ArrayList<City>();
		try {
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			list=dao.queryCityByProvince(p);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	public List<City> queryCityByID(String id){
		Connection conn=null;
		List<City> list=new ArrayList<City>();
		try {
			conn=DBUtil.getConn();
			CityDaoImpl dao=new CityDaoImpl(conn);
			list=dao.queryCityByID(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}

}
