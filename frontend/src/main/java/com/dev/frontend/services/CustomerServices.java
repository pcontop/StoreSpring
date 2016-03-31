package com.dev.frontend.services;

import java.util.Arrays;
import java.util.List;

import com.dev.beans.CustomerDto;

/**
 * Implementation of RestServices for the Customer CRUD operations. 
 * 
 * @author pcont_000
 *
 */
public class CustomerServices extends RestServices<CustomerDto>{
	private final static String CUSTOMER_DIRECTORY = "/customer/";
		
	public CustomerServices(){
	}
		
	@Override
	public String getServiceDirectory() {
		return CUSTOMER_DIRECTORY;
	}

	@Override
	public List<CustomerDto> getAllOfType() {
		CustomerDto[] result = restTemplate.getForObject(buildUrl(""),
					CustomerDto[].class);
			return Arrays.asList(result);

	}
		

}
