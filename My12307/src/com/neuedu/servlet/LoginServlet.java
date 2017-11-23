package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.UserService;
import com.neuedu.domain.User;
import com.neuedu.util.Constants;
import com.neuedu.util.Md5Utils;
import com.neuedu.util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=StringUtil.parseNull(request,"action");
		if("modifyPwd".equals(action)) {
			doModifyPwd(request,response);
		}else {
			doLogin(request, response);
		}
		
	}
	
	public void doLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String loginName=StringUtil.parseNull(request,"loginName");
		String pwd=StringUtil.parseNull(request,"pwd");
		//对密码进行md5加密存储
		pwd=Md5Utils.md5(pwd);
		String validateCode=StringUtil.parseNull(request,"validateCode");
		String autoLogin=StringUtil.parseNull(request,"autoLogin");
		if("".equals(autoLogin)) {
			autoLogin="false";
		}
		System.out.println("loginName="+loginName);
		System.out.println("pwd="+pwd);
		System.out.println("validateCode="+validateCode);
		System.out.println("autoLogin="+autoLogin);
		
		//验证验证码是否输入正确
		if(!validateCode.equalsIgnoreCase(
				(String)request.getSession().getAttribute("code"))) {
			//登录失败
			request.setAttribute("msg", "登录失败，请输入正确的验证码");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
		
		UserService us=UserService.getInstance();
		User user=us.login(loginName, pwd);
		if(user!=null) {
			//登陆成功
			/**
			 * 登录成功后把用户对象存放到session中
			 * session可以在所有页面之间共享数据
			 * 前提是session是有效的
			 */
			request.getSession().setAttribute(Constants.userKey, user);
			//把当前登录成功的用户名通过cookie写给浏览器
			Cookie cook=new Cookie("loginName",loginName);
			//设置cookie的存活时间，以秒为单位(默认临时的，浏览器关闭即被清除)
			cook.setMaxAge(5*60);
			cook.setPath(request.getContextPath());
			response.addCookie(cook);
			if("1".equals(user.getRule())) {
				response.sendRedirect(request.getContextPath()+"/Admin/Index.jsp");
			}else if("2".equals(user.getRule())){
				response.sendRedirect(request.getContextPath()+"/User/Index.html");
			}
			
		}else {
			//登录失败
			request.setAttribute("msg", "登录失败，请输入正确的用户信息");
			//response.sendRedirect(request.getContextPath()+"/Login.jsp");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}
	
	public void doModifyPwd(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String userID=StringUtil.parseNull(req,"userID");
		String newPwd=StringUtil.parseNull(req,"pwd");
		newPwd=Md5Utils.md5(newPwd);
		String oldPwd=StringUtil.parseNull(req,"oldPwd");
		oldPwd=Md5Utils.md5(oldPwd);
		UserService us=UserService.getInstance();
		boolean flag=us.changePwd(Integer.parseInt(userID), newPwd, oldPwd);
		if(flag) {
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<p color='red'>恭喜你，密码修改成功</p><script>alert('恭喜你，密码修改成功');</script>");
			pw.println("</body></html>");
			pw.flush();
			pw.close();
		}else {
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.println("<html><title>密码修改提示信息</title><body>");
			pw.println("<p color='red'>抱歉，密码修改失败，请确认输入的原密码是否正确</p><script>alert('抱歉，密码修改失败，请确认输入的原密码是否正确');</script>");
			pw.println("</body></html>");
			pw.flush();
			pw.close();
		}
	}

}
