package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
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
import com.neuedu.util.Md5Utils;
import com.neuedu.util.StringUtil;

public class RegisterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String action=StringUtil.parseNull(req,"action");
		if("check".equals(action)) {
			this.doCheck(req, resp);
		}else {
			this.doReg(req, resp);
		}
																				
	}
	
	/*private boolean isLoginNameExists(String loginName,HttpServletResponse resp) throws IOException{
		UserService userSer=UserService.getInstance();
		boolean flag=userSer.isLoginNameExists(loginName);
		if(flag) {
			//resp.setCharacterEncoding("gb2312");
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>注册提示信息</title><body>");
			pw.println("<p color='red'>注册失败，用户名已经存在</p><script>alert('注册失败，用户名已经存在');</script>");
			pw.println("</body></html>");
			pw.flush();
			pw.close();
		}
		return flag;
	}*/
	
	/**
	 * 验证要注册的登录名是否存在
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void doCheck(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));
		UserService userSer=UserService.getInstance();
		boolean flag=userSer.isLoginNameExists(loginName);
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.print(flag?"exists":"");
		pw.flush();
		pw.close();
	}
	
	/**
	 * 执行用户注册
	 * @param req
	 * @param resp
	 * @return true:注册成功  false:注册失败
	 * @throws IOException 
	 */
	private void doReg(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String loginName=StringUtil.parseNull(req.getParameter("loginName"));
		String pwd=StringUtil.parseNull(req.getParameter("pwd"));
		//对密码进行md5加密存储
		pwd=Md5Utils.md5(pwd);
		String pwd2=StringUtil.parseNull(req.getParameter("pwd2"));
		String rule=StringUtil.parseNull(req.getParameter("rule"));
		if("管理员".equals(rule)){
			rule="1";
		}else{
			rule="2";
		}

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
		
		//把获取的参数信息，调用service和dao完成数据保存		
		Province prov=new Province(-1,"-1","");
		CityService citySer=CityService.getInstance();
		String cityId=citySer.queryCityID(city);
		City cityObj=new City(-1,cityId,city,prov);
		
		UserType uType=new UserType((Integer.parseInt(passenger_type)), "");
		
		CertType cType=new CertType((Integer.parseInt(idTypeCode)), "");
		
		User user=new User(-1,cityObj,uType,cType,loginName,pwd,rule,realName,sexCode,
				idCode,birth,remark,"1",ip,"");		
		
		UserService userSer=UserService.getInstance();
		boolean saveFlag=userSer.addUser(user);
		if(saveFlag) {
			//使用重定向方式，跳转页面到登录页面
			
			//resp.sendRedirect(req.getContextPath()+"/Login.jsp?loginName="+loginName);
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.print("success");
			pw.flush();
			pw.close();
			//使用请求转发
			/*try {
				req.getRequestDispatcher("/Login.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}else if(saveFlag==false) {
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.print("error");
			pw.flush();
			pw.close();
			}
	}

	

}
