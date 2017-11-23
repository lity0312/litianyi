package com.neuedu.business.dao;

import java.sql.Connection;
import java.util.List;

import com.neuedu.business.dao.impl.ProvinceDaoImpl;
import com.neuedu.domain.Province;

public interface ProvinceDao {
	
	
	public List<Province> queryProvince()throws Exception;

}
