package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.CityService;
import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.City;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.Md5Utils;
import com.neuedu.util.StringUtil;

/**
 * Servlet implementation class MgrModifyServlet
 */
@WebServlet("/Admin/MgrModifyServlet")
public class MgrModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MgrModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("开始编辑");
		String action=StringUtil.parseNull(request,"action");
		if(action.equals("modifyPwd")) {
			doPwd(request, response);
		}else if(action.equals("modify")){
			
				doModify(request, response);
			
		}else {
			String id=StringUtil.parseNull(request, "action");
			UserService us=UserService.getInstance();
			User user=us.queryUserByID(Integer.parseInt(id));
			System.out.println("user="+user);
			request.getSession().setAttribute("pageUserParam", user);
			request.getRequestDispatcher("../Admin/UserManageInfo_Amind_Edit.jsp").forward(request, response);
		}
			
		
	}
	
	public void doPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService us=UserService.getInstance();
		String pwd=Md5Utils.md5("123");
		boolean flag=us.changePwd(((User)request.getSession().getAttribute("pageUserParam")).getId(),
				pwd, ((User)request.getSession().getAttribute("pageUserParam")).getPassword());
		if(flag){
			
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<script>alert('密码修改成功');</script>");
			pw.println("<script>window.opener.location.reload();</script>");
			pw.println("<script>window.close();</script>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
		}else{
			response.setContentType("text/html");
			PrintWriter pw=((ServletResponse) request).getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<script>alert('初始化密码失败，请稍后重试！');</script>");
			pw.println("</body><html>");
			pw.flush();
			pw.close();
		}
	}
	
	public void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("修改信息！");
		String loginName=StringUtil.parseNull(request.getParameter("loginName"));
      	String userId1=StringUtil.parseNull(request,"id");
      	String realName=StringUtil.parseNull(request.getParameter("realName"));
      	String sex=StringUtil.parseNull(request.getParameter("sexCode"));
      	String rule=StringUtil.parseNull(request.getParameter("rule"));
      	String city=StringUtil.parseNull(request.getParameter("city"));
      	String idTypeCode=StringUtil.parseNull(request.getParameter("idTypeCode"));
      	String idCode=StringUtil.parseNull(request.getParameter("idCode"));
      	String birthday=StringUtil.parseNull(request.getParameter("birthday"));
      	String passenger_type=StringUtil.parseNull(request.getParameter("passenger_type"));
      	String remark=StringUtil.parseNull(request.getParameter("remark"));   
      //1、组装User对象
        SimpleDateFormat dtf=new SimpleDateFormat("yyyy-MM-dd");
       	Date birth=null;
 		try {
 			birth = dtf.parse(birthday.toString());
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
       	String ip=request.getRemoteAddr();
       
       	
       	CityService citySer=CityService.getInstance();
       	String cityId=citySer.queryCityID(city);
       	City cityObj=new City(-1,cityId,city,null);
       	
       	UserType uType=new UserType(Integer.parseInt(passenger_type),"");
       	CertType cType=new CertType(Integer.parseInt(idTypeCode),"");
	     //2、调用service方法进行修改
       	UserService userSer=UserService.getInstance();
       	
      	User user=new User(Integer.parseInt(userId1),cityObj,uType,cType,loginName,"",rule,realName,sex,idCode,birth,remark,"1",ip,null);
      	System.out.println("user"+user);
      	
      	int saveFlag=userSer.updateUser(user);
      	 if(saveFlag>0) {
      		response.setContentType("text/html");
          	PrintWriter pw=response.getWriter();
          	pw.println("<html><title>修改成功</title><body>");
	      	pw.println("<script>alert('信息修改成功')</script>");
	      	pw.println("<script>window.opener.location.reload();</script>");
			pw.println("<script>window.close();</script>");
	      	pw.println("</body></html>");
          	pw.flush();
          	pw.close();	
      	}else{
      		//成功给予提醒
          	//resp.setCharacterEncoding("gb2312");
          	response.setContentType("text/html");
          	PrintWriter pw=response.getWriter();
          	pw.println("<html><script>");
          	pw.println("alert('数据保存失败，请稍候重试！')");
          	pw.println("</script></html>");
          	pw.flush();
          	pw.close();	
      	}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
