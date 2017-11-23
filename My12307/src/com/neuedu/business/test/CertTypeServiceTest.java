package com.neuedu.business.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.neuedu.business.service.CertTypeService;
import com.neuedu.domain.CertType;

public class CertTypeServiceTest {

	@Test
	public void testQueryCertType() {
		CertTypeService cs=CertTypeService.getInstance();
		System.out.println(cs.queryCertType());
		List<CertType> list=cs.queryCertType();
		assertEquals(4,list.size());
	}

}
