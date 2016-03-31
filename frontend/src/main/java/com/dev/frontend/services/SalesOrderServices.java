package com.dev.frontend.services;

import java.util.Arrays;
import java.util.List;

import com.dev.beans.SalesOrderDto;

/**
 * Implementation of RestServices for the Sales Order CRUD operations. 
 * 
 * @author pcont_000
 *
 */
public class SalesOrderServices extends RestServices<SalesOrderDto>{
	private final static String SERVICE_DIRECTORY = "/salesOrder/";
		
	public SalesOrderServices(){
	}
		
	@Override
	public String getServiceDirectory() {
		return SERVICE_DIRECTORY;
	}

	@Override
	public List<SalesOrderDto> getAllOfType() {
		SalesOrderDto[] result = restTemplate.getForObject(buildUrl(""),
				SalesOrderDto[].class);
			return Arrays.asList(result);

	}
		

}
