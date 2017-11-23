package com.neuedu.business.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.impl.CertTypeDaoImpl;
import com.neuedu.business.dao.impl.UserTypeDaoImpl;
import com.neuedu.domain.CertType;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;

public class CertTypeService extends BaseService{
	
	private static final CertTypeService instance=new CertTypeService();
	private CertTypeService() {}
	public static CertTypeService getInstance() {
		return instance;
	}
	
	public List<CertType> queryCertType(){
		Connection conn=null;
		List<CertType>res=new ArrayList<>();
		try {
			conn=DBUtil.getConn();
			CertTypeDaoImpl dao=new CertTypeDaoImpl(conn);
			res=dao.queryCertType();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return res;
	}

}
