package com.neuedu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.neuedu.business.service.CityService;
import com.neuedu.domain.City;

public class CityServiceTest {
	
	CityService test=CityService.getInstance();

	@Test
	public void testQueryCityID() {
		List<City> list=test.queryCityByID("130000");
		assertEquals(11,list.size());
	}

	@Test
	public void testQueryCity() {
		List<City> list=test.queryCity();
		assertEquals(11,list.size());
	}


}
