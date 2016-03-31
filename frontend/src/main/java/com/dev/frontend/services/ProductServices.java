package com.dev.frontend.services;

import java.util.Arrays;
import java.util.List;

import com.dev.beans.ProductDto;

/**
 * Implementation of RestServices for the Product CRUD operations. 
 * 
 * @author pcont_000
 *
 */
public class ProductServices extends RestServices<ProductDto>{
	private final static String PRODUCT_DIRECTORY = "/product/";
		
	public ProductServices(){
	}
		
	@Override
	public String getServiceDirectory() {
		return PRODUCT_DIRECTORY;
	}

	@Override
	public List<ProductDto> getAllOfType() {
		ProductDto [] result = restTemplate.getForObject(buildUrl(""),
					ProductDto[].class);
			return Arrays.asList(result);

	}
		

}
