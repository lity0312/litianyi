package com.neuedu.servlet;

import java.util.List;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.CityService;
import com.neuedu.business.service.ProvinceService;
import com.neuedu.domain.City;
import com.neuedu.util.StringUtil;


@WebServlet("/test")
public class TestServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse resp) {
		doPost(req,resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) {
		String type=StringUtil.parseNull(req,"type");
		if(type.equals("city")) {
			CityService city=CityService.getInstance();
			List<City> list=city.queryCity();
			System.out.println(list);
		}else if(type.equals("province")) {
			ProvinceService pro=ProvinceService.getInstance();
			System.out.println(pro.queryProvince());
		}else {
			System.out.println("请输入正确的参数");
		}
	}
}
