package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.CityService;
import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.Province;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.StringUtil;

public class RegisterServlet3 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("gb2312");
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));		
		String pwd=StringUtil.parseNull(req.getParameter("pwd"));
		String pwd2=StringUtil.parseNull(req.getParameter("pwd2"));
		String realName=StringUtil.parseNull(req.getParameter("realName"));
		String sexCode=StringUtil.parseNull(req.getParameter("sexCode"));
		String province=StringUtil.parseNull(req.getParameter("province"));
		String city=StringUtil.parseNull(req.getParameter("city"));
		String idTypeCode=StringUtil.parseNull(req.getParameter("idTypeCode"));
		String idCode=StringUtil.parseNull(req.getParameter("idCode"));
		String birthday=StringUtil.parseNull(req.getParameter("birthday"));
		String passenger_type=StringUtil.parseNull(req.getParameter("passenger_type"));
		String remark=StringUtil.parseNull(req.getParameter("remark"));
		
		SimpleDateFormat dtf=new SimpleDateFormat("yyyy-MM-dd");
		Date birth=null;
		try {
			birth = dtf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip=req.getRemoteAddr();
		
		System.out.println("loginName="+loginName);
		System.out.println("pwd="+pwd);
		System.out.println("pwd2="+pwd2);
		System.out.println("realName="+realName);
		System.out.println("sexCode="+sexCode);
		System.out.println("province="+province);
		System.out.println("city="+city);
		System.out.println("idTypeCode="+idTypeCode);
		System.out.println("idCode="+idCode);
		System.out.println("birthday="+birthday);
		System.out.println("passenger_type="+passenger_type);
		System.out.println("remark="+remark);		
		
		//把获取的参数信息，调用service和dao完成数据保存		
		Province prov=new Province(-1,"-1","");
		CityService citySer=CityService.getInstance();
		String cityId=citySer.queryCityID(city);
		City cityObj=new City(-1,cityId,city,prov);
		
		UserType uType=new UserType((Integer.parseInt(passenger_type)), "");
		
		CertType cType=new CertType((Integer.parseInt(idTypeCode)), "");
		
		User user=new User(-1,cityObj,uType,cType,loginName,pwd,"2",realName,sexCode,
				idCode,birth,remark,"1",ip,"");		
		
		UserService userSer=UserService.getInstance();
		boolean saveFlag=userSer.addUser(user);
		String tip="注册成功!";
		if(saveFlag==false) {
			tip="注册失败!";
		}
		//成功给予提醒
		resp.setCharacterEncoding("gb2312");
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><title>注册提示信息</title><body>");
		pw.println("<p color='red'>"+tip+"</p><script>alert('"+tip+"');</script>");
		pw.println("</body></html>");
		pw.flush();
		pw.close();
		
		
		
		
		
		
		
		
		
	}

}
