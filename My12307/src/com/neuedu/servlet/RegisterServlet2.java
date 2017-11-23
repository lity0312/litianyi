package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.util.StringUtil;

public class RegisterServlet2 extends HttpServlet {
	
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
		
		/**
		 * 对于checkbox的值，需要考虑以下情况
		 * 1.根据名称获取的是页面中被选中的checkbox的值
		 * 2.如果使用getParameter方法，获取的是种种里面的第一个
		 * 3.如果需要获取全部被选中的，那么使用getParameterValues
		 * 4.如果checkbox没有一个被选中，那么req.getParamterValues方法返回null，需要进行空指针处理
		 */
		
		//String[] peopleArray=req.getParameterValues("people");
		String[] peopleArray=StringUtil.parseNullArray(req,"people");
		
		//两种方法都可以对param参数中的null进行处理
		String people=StringUtil.parseNull(req.getParameter("people"));
		people=StringUtil.parseNull(req,"people");
		
		for(String str:peopleArray) {
			System.out.println("peopleArray="+str);
		}
		
		
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
		System.out.println("people="+people);
		
		
		//把获取的参数信息，调用service和dao完成数据保存
		
		//成功给予提醒
		resp.setCharacterEncoding("gb2312");
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.println("<html><title>注册提示信息</title><body>");
		pw.println("<p color='red'>注册成功</p><script>alert('恭喜你，注册成功！');</script>");
		pw.println("</body></html>");
		pw.flush();
		pw.close();
		
		
		
		
		
		
		
		
		
	}

}
