package com.neuedu.business.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.business.dao.UserDao;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.DBUtil;
import com.neuedu.util.PageBean;
import com.neuedu.util.ResultSetCallBack;


public class UserDaoImpl implements UserDao {
	
	//数据库连接
	private Connection conn=null;
	//构造dao时传递数据库连接进来
	public UserDaoImpl(Connection conn) {
		this.conn=conn;
	}

	@Override
	public boolean addUser(User user) throws Exception {
		String sql="insert into tab_user(id, city, "
				+ "user_type, cert_type, username, password, rule, "
				+ "realname, sex,cert, birthday, content, status,"
				+ " login_ip, image_path)values(tab_user_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[] {Integer.parseInt(user.getCity().getCityId()),
				user.getUserType().getId(),
				user.getCertType().getId(),
				user.getUserName(),
				user.getPassword(),
				user.getRule(),
				user.getRealName(),
				user.getSex(),
				user.getCert(),
				new java.sql.Date(user.getBirthday().getTime()),
				user.getContent(),
				user.getStatus(),
				user.getLoginIp(),
				user.getImagePath()};
		int res=DBUtil.execSQL(conn, sql, params);
		if(res==1) {
			return true;
		}else {
		return false;
		}
	
		
		/*PreparedStatement stmt=null;
		try {
			stmt=conn.prepareStatement("insert into tab_user(id, city, user_type, cert_type, username, password, rule, realname, sex,cert, birthday, content, status, login_ip, image_path)values(tab_user_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,Integer.parseInt(user.getCity().getCityId()));
			stmt.setInt(2, user.getUserType().getId());
			stmt.setInt(3, user.getCertType().getId());
			stmt.setString(4, user.getUserName());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, user.getRule());
			stmt.setString(7, user.getRealName());
			stmt.setString(8, user.getSex());
			stmt.setString(9, user.getCert());
			stmt.setDate(10, new java.sql.Date(user.getBirthday().getTime()));
			stmt.setString(11, user.getContent());
			stmt.setString(12, user.getStatus());
			stmt.setString(13, user.getLoginIp());
			stmt.setString(14, user.getImagePath());
			int res=stmt.executeUpdate();			
			if(res==1) {
				return true;
			}else {
			return false;
			}
		}finally {
			DBUtil.close(stmt);
		}*/
	}

	@Override
	public int delUser(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delUser(String[] ids) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("delete from tab_user where id in(");
		for(String i:ids){
			sb.append(i+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		int res=DBUtil.execSQL(conn,sb.toString());
		//执行
		return res;
	}

	@Override
	public int updateUser(User user) throws Exception {
		String sql="update tab_user a set"
				+ " a.cert_type=?,"
				+ "a.city=(select c.cityid from tab_city c where c.city=?),"
				+ "a.user_type=?,"
				+ "a.username=?,a.realname=?, a.sex=?,a.cert=?,"
				+ "a.birthday=?,a.content=?,a.login_ip=?,"
				+ "a.image_path=? where a.id=?";
		int res=DBUtil.execSQL(conn,sql, 
					new Object[]{user.getCertType().getId(),
							user.getCity().getCity(),
							user.getUserType().getId(),
							user.getUserName(),
							user.getRealName(),
							user.getSex(),
							user.getCert(),
							new java.sql.Date(user.getBirthday().getTime()),
							user.getContent(),
							user.getLoginIp(),
							user.getImagePath(),
							user.getId()});
		//执行
		return res;
	}

	@Override
	public int ModifyPwd(int userId, String newPwd,String oldPwd) throws Exception {
		PreparedStatement stmt=null;
		int recordCnt=0;
		try {
			stmt=conn.prepareStatement("update tab_user a set a.password=? where a.id=? and a.password=?");
			stmt.setString(1, newPwd);
			stmt.setInt(2, userId);
			stmt.setString(3, oldPwd);
			recordCnt=stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt); 
		}
		return recordCnt;
	}

	@Override
	public User queryUserByID(int userId) throws Exception {
		String str="select a.*,"
				+ "b.content cert_type_name,"
				+ "c.content user_type_name,"
				+ "d.city city_name,d.id city_id,"
				+ "e.province province_name,e.id p_id,"
				+ "e.provinceid provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e "
				+ "where a.cert_type=b.id and a.user_type=c.id "
				+ "and a.city=d.cityid(+) and d.father=e.provinceid(+) "
				+ "and a.id=?";
		
		return DBUtil.queryByCallBack(conn, 
				str,
				new Object[]{userId},
				new ResultSetCallBack<User>(){
				
					@Override
					public User parseResultSet(ResultSet rs) throws SQLException {
						User user=new User();
						if(rs.next()){
							user.setId(rs.getInt("ID"));
		 					user.setPassword(rs.getString("PASSWORD"));
							user.setUserName(rs.getString("USERNAME"));
							user.setRule(rs.getString("RULE"));
							user.setRealName(rs.getString("REALNAME"));
							user.setSex(rs.getString("SEX"));
							user.setBirthday(rs.getDate("BIRTHDAY"));
							user.setCert(rs.getString("CERT"));
							//组装证件类型对象
							CertType cType=new CertType();
							cType.setId(rs.getInt("CERT_TYPE"));
							cType.setContent(rs.getString("CERT_TYPE_NAME"));
							user.setCertType(cType);
							//组装城市类型对象
							Province province=new Province();
							province.setProvince(rs.getString("PROVINCE_NAME"));
							province.setId(rs.getInt("P_ID"));
							province.setProvinceId(rs.getString("PROVINCEID"));
							City city=new City();
							city.setCity(rs.getString("CITY_NAME"));
							city.setId(rs.getInt("CITY_ID"));
							//tab_user的city
							city.setCityId(rs.getString("CITY"));
							city.setProvince(province);
							user.setContent(rs.getString("CONTENT"));
							user.setImagePath(rs.getString("IMAGE_PATH"));
							user.setLoginIp(rs.getString("LOGIN_IP"));
							user.setStatus(rs.getString("status"));
							user.setCity(city);
							//组装用户类型
							UserType userType=new UserType();
							userType.setId(rs.getInt("USER_TYPE"));
							userType.setContent(rs.getString("USER_TYPE_NAME"));
							user.setUserType(userType);
						}
						return user;
					}
		}); 
		
	}

	@Override
	public List<User> queryUsers(User user) throws Exception {
		String sql="select a.*, b.content  cert_type_name, "
				+ "c.content user_type_name, d.city city_name,d.id c_id,"
				+ " e.province province_name,e.id p_id,e.provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e where a.cert_type = b.id "
				+ "and a.user_type = c.id and a.city = d.cityid(+) and d.father = e.provinceid(+) ";
		return DBUtil.queryByCallBack(conn, sql,new ResultSetCallBack<List<User>>() {
			

			@Override
			public List<User> parseResultSet(ResultSet rs) throws SQLException {
				List<User> list=new ArrayList<>();
				while(rs.next()) {
					User user=new User();
		    	    user.setId(rs.getInt("ID"));
		    	    user.setUserName(rs.getString("USERNAME"));
		    	    user.setRule(rs.getString("RULE"));
		    	    user.setRealName(rs.getString("REALNAME"));
		    	    user.setSex(rs.getString("SEX"));
		    	    user.setBirthday(rs.getDate("BIRTHDAY"));
		    	    user.setCert(rs.getString("CERT"));
		    	    //组装证件类型对象
		    	    CertType cType=new CertType();
		    	    cType.setId(rs.getInt("CERT_TYPE"));
		    	    cType.setContent(rs.getString("CERT_TYPE_NAME"));
		    	    user.setCertType(cType);
		    	    //组装城市信息对象
		    	    Province pro=new Province();
		    	    pro.setId(rs.getInt("P_ID"));
		    	    pro.setProvinceId(rs.getString("PROVINCEID"));
		    	    pro.setProvince(rs.getString("PROVINCE_NAME"));
		    	    City city=new City();
		    	    city.setCity(rs.getString("CITY_NAME"));
		    	    city.setCityId(rs.getString("CITY"));
		    	    city.setId(rs.getInt("C_ID"));
		    	    city.setProvince(pro);
		    	    user.setCity(city);
		    	    user.setContent(rs.getString("CONTENT"));
		    	    user.setImagePath(rs.getString("IMAGE_PATH"));
		    	    user.setLoginIp(rs.getString("LOGIN_IP"));
		    	    user.setStatus(rs.getString("STATUS"));
		    	    //组装用户类型
		    	    UserType userType=new UserType();
		    	    userType.setId(rs.getInt("USER_TYPE"));
		    	    userType.setContent(rs.getString("USER_TYPE_NAME"));
		    	    user.setUserType(userType);
		    	    list.add(user);
				}
				
		    	return list;
			}
			
		});
	}

	@Override
	public User login(String loginName, String pwd) throws Exception {
		String sql="select a.*, b.content  cert_type_name, "
				+ "c.content user_type_name, d.city city_name,d.id c_id,"
				+ " e.province province_name,e.id p_id,e.provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e where a.cert_type = b.id "
				+ "and a.user_type = c.id and a.city = d.cityid(+) and d.father = e.provinceid(+) "
				+ "and a.username=? and a.password=?";
		Object[] params=new Object[] {loginName,pwd};
		return DBUtil.queryByCallBack(conn, sql,params, new ResultSetCallBack<User>() {
			

			@Override
			public User parseResultSet(ResultSet rs) throws SQLException {
				User user=null;
				if(rs.next()) {
					user=new User();
		    	    user.setId(rs.getInt("ID"));
		    	    user.setUserName(rs.getString("USERNAME"));
		    	    user.setRule(rs.getString("RULE"));
		    	    user.setRealName(rs.getString("REALNAME"));
		    	    user.setSex(rs.getString("SEX"));
		    	    user.setBirthday(rs.getDate("BIRTHDAY"));
		    	    user.setCert(rs.getString("CERT"));
		    	    //组装证件类型对象
		    	    CertType cType=new CertType();
		    	    cType.setId(rs.getInt("CERT_TYPE"));
		    	    cType.setContent(rs.getString("CERT_TYPE_NAME"));
		    	    user.setCertType(cType);
		    	    //组装城市信息对象
		    	    Province pro=new Province();
		    	    pro.setId(rs.getInt("P_ID"));
		    	    pro.setProvinceId(rs.getString("PROVINCEID"));
		    	    pro.setProvince(rs.getString("PROVINCE_NAME"));
		    	    City city=new City();
		    	    city.setCity(rs.getString("CITY_NAME"));
		    	    city.setCityId(rs.getString("CITY"));
		    	    city.setId(rs.getInt("C_ID"));
		    	    city.setProvince(pro);
		    	    user.setCity(city);
		    	    user.setContent(rs.getString("CONTENT"));
		    	    user.setImagePath(rs.getString("IMAGE_PATH"));
		    	    user.setLoginIp(rs.getString("LOGIN_IP"));
		    	    user.setStatus(rs.getString("STATUS"));
		    	    //组装用户类型
		    	    UserType userType=new UserType();
		    	    userType.setId(rs.getInt("USER_TYPE"));
		    	    userType.setContent(rs.getString("USER_TYPE_NAME"));
		    	    user.setUserType(userType);
				}
				
		    	return user;
			}
			
		});
		/*PreparedStatement stmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			stmt=conn.prepareStatement("select * from tab_user where username=? and password=?");
		    stmt.setString(1, loginName);
		    stmt.setString(2,pwd);
		    rs=stmt.executeQuery();
		    if(rs.next()) {
		    	user=new User();
		    	user.setId(rs.getInt("ID"));
		    	user.setUserName(rs.getString("USERNAME"));
		    	user.setRule(rs.getString("RULE"));
		    	user.setRealName(rs.getString("REALNAME"));
		    }
		} finally {
			DBUtil.close(rs, stmt);
		}
		return user;*/
	}

	@Override
	public int isLoginNameExists(String loginName) throws Exception {
		String sql="select count(1) cnt from tab_user where username=?";
		int cnt=DBUtil.queryForInt(conn, sql, new Object[] {loginName});
		return cnt;
		/*PreparedStatement stmt=null;
		ResultSet rs=null;
		int cnt=0;
		try {
			stmt=conn.prepareStatement("select count(1) cnt from tab_user where username=?");
			stmt.setString(1, loginName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("CNT");
			}
		} finally {
			DBUtil.close(rs, stmt);
		}
		return cnt;*/
	}

	@Override
	public List<User> queryUsers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryUsers(User user, PageBean page) throws Exception {
		String sql="select a.*, b.content  cert_type_name, "
				+ "c.content user_type_name, d.city city_name,d.id c_id,"
				+ " e.province province_name,e.id p_id,e.provinceid from tab_user a,tab_certtype b,"
				+ "tab_usertype c,tab_city d,tab_province e where a.cert_type = b.id "
				+ "and a.user_type = c.id and a.city = d.cityid(+) and d.father = e.provinceid(+) ";
		if(null!=user.getUserName()&& !user.getUserName().equals("")) {
			sql+=" and a.username like '%"+user.getUserName()+"%'";
		}
		if(null!=user.getSex()&&!user.getSex().equals("")) {
			sql+=" and a.sex='"+user.getSex()+"'";
		}
		if(0!=user.getCertType().getId()) {
			sql+=" and a.cert_type="+user.getCertType().getId()+"";
		}
		if(null!=user.getCert()&&!user.getCert().equals("")) {
			sql+=" and a.cert='"+user.getCert()+"'";
		}
		if(0!=user.getUserType().getId()) {
			sql+=" and a.user_type="+user.getUserType().getId()+"";
		}
		
		//要查询全部记录数
		int recordCnt=DBUtil.queryForInt(conn, "select count(1) from ("+sql+")");
		page.setRecordCnt(recordCnt);
		//把SQL排序写在计算总记录数之后，节省数据库资源
		sql+=" order by a.username asc";
		return DBUtil.queryByCallBackPage(conn, sql,page,new ResultSetCallBack<List<User>>() {
			
			@Override
			public List<User> parseResultSet(ResultSet rs) throws SQLException {
				List<User> list=new ArrayList<>();
				while(rs.next()) {
					User user=new User();
		    	    user.setId(rs.getInt("ID"));
		    	    user.setUserName(rs.getString("USERNAME"));
		    	    user.setRule(rs.getString("RULE"));
		    	    user.setRealName(rs.getString("REALNAME"));
		    	    user.setSex(rs.getString("SEX"));
		    	    user.setBirthday(rs.getDate("BIRTHDAY"));
		    	    user.setCert(rs.getString("CERT"));
		    	    //组装证件类型对象
		    	    CertType cType=new CertType();
		    	    cType.setId(rs.getInt("CERT_TYPE"));
		    	    cType.setContent(rs.getString("CERT_TYPE_NAME"));
		    	    user.setCertType(cType);
		    	    //组装城市信息对象
		    	    Province pro=new Province();
		    	    pro.setId(rs.getInt("P_ID"));
		    	    pro.setProvinceId(rs.getString("PROVINCEID"));
		    	    pro.setProvince(rs.getString("PROVINCE_NAME"));
		    	    City city=new City();
		    	    city.setCity(rs.getString("CITY_NAME"));
		    	    city.setCityId(rs.getString("CITY"));
		    	    city.setId(rs.getInt("C_ID"));
		    	    city.setProvince(pro);
		    	    user.setCity(city);
		    	    user.setContent(rs.getString("CONTENT"));
		    	    user.setImagePath(rs.getString("IMAGE_PATH"));
		    	    user.setLoginIp(rs.getString("LOGIN_IP"));
		    	    user.setStatus(rs.getString("STATUS"));
		    	    //组装用户类型
		    	    UserType userType=new UserType();
		    	    userType.setId(rs.getInt("USER_TYPE"));
		    	    userType.setContent(rs.getString("USER_TYPE_NAME"));
		    	    user.setUserType(userType);
		    	    list.add(user);
				}
				
		    	return list;
			}
			
		});
	}

	

}
