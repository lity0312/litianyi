package com.neuedu.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.util.Constants;

 
/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {
	
	private String loginPath=null;

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//转为httpservlet对象进行http处理
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		if(null!=req.getSession().getAttribute(Constants.userKey)) {
			// session中存在用户信息说明登陆成功，放行
		    chain.doFilter(request, response);
		}else {
			String url=req.getRequestURI();
			String[] pathArray=loginPath.split(";");
			for(String path:pathArray) {
				if(null!=url&&url.endsWith(path)) {
				chain.doFilter(request, response);
				return;
			   }
			}
			
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			pw.print("<html><body>");
			pw.print("<script>");
			pw.print("alert('您的会话超时或者您从未登录，请重新登陆!');");
			pw.print("window.top.location.href='"+req.getContextPath()+"/Login.jsp';");
			pw.print("</script>");
			pw.print("</body></html>");
			pw.flush();
			pw.close();
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig cfg) throws ServletException {
		loginPath = cfg.getInitParameter("loginPath");
	}

}
