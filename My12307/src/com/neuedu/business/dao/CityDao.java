package com.neuedu.business.dao;

import java.util.List;

import com.neuedu.domain.City;
import com.neuedu.domain.Province;

public interface CityDao {
	
	/**
	 * 获取所有城市信息
	 * @return
	 */	
	public List<City> queryCity()throws Exception;
	
	/**
	 * 根据省份对象查询城市信息
	 * @param p
	 * @return
	 */
	public List<City> queryCityByProvince(Province p)throws Exception;
	
	/**
	 * 根据省份编码查询对应城市
	 * @param id
	 * @return
	 */	
	public List<City> queryCityByID(String id)throws Exception;
	
	/**
	 * 根据城市名获取城市id
	 * @param name
	 * @return
	 */
	public String queryCityID(String name)throws Exception;

}
