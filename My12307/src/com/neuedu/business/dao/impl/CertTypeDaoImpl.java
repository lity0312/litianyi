package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.CertTypeDao;
import com.neuedu.domain.CertType;

import com.neuedu.util.DBUtil;
import com.neuedu.util.ResultSetCallBack;

public class CertTypeDaoImpl implements CertTypeDao {

	private Connection conn;

	public CertTypeDaoImpl(Connection conn) {
		this.conn=conn;
	}

	@Override
	public List<CertType> queryCertType() throws Exception{
		String sql="select id, content from tab_usertype";
		return DBUtil.queryByCallBack(conn, sql, new ResultSetCallBack<List<CertType>>() {

			@Override
			public List<CertType> parseResultSet(ResultSet rs) throws SQLException {
				List<CertType> list=new ArrayList<CertType>();
				while(rs.next()) {
					CertType type=new CertType();
					type.setId(rs.getInt("ID"));
					type.setContent(rs.getString("CONTENT"));
					list.add(type);
				}
				return list;
			}
			
		});
		/*PreparedStatement stmt=null;
		ResultSet rs=null;
		List<CertType> list=new ArrayList<CertType>();
		try {
			stmt=conn.prepareStatement("select id, content from tab_certtype");
			rs=stmt.executeQuery();
			while(rs.next()) {
				CertType cType=new CertType(rs.getInt("ID"),rs.getString("CONTENT"));
				list.add(cType);
			}
		} finally {
			DBUtil.close(rs, stmt);
		}
		return list;*/
	}

}
