package com.neuedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCharacterEncodingFilter implements Filter {

	private String charSet="";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("正在被执行过滤:"+((HttpServletRequest)req).getRequestURI());
		System.out.println("sessionID="+((HttpServletRequest)req).getSession().getId());
		/**
		 * session的存活时间为30秒
		 */
		//((HttpServletRequest).req).getSession.setMaxInctiveInterval(30);
		HttpServletRequest req2=((HttpServletRequest)req);
		if(req2.getHeader("X-Requested-With")!=null&&req2.getHeader("X-Requested-With").
				equalsIgnoreCase("XMLHttpRequest")) {
			req.setCharacterEncoding("utf-8");
		}else {
			req.setCharacterEncoding(charSet);
		}
		
		resp.setCharacterEncoding(charSet);
		//放行到下一个过滤器，如果这是最后一个，那么请求到达目标资源
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		charSet=cfg.getInitParameter("charSet");
		System.out.println("charSet="+charSet);

	}

}
