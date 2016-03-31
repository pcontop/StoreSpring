package com.dev.backend.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dev.backend.bean.TestUtil;
import com.dev.backend.service.PersistService;
import com.dev.beans.ProductDto;

@Service @Primary
class PersistProductServiceStub implements PersistService<ProductDto>{

	@Override
	public boolean save(ProductDto productDto) {
		return true;
	}

	@Override
	public ProductDto get(String code) {
		return ProductDto.create().setCode(code).build();
	}
	
	@Override
	public boolean delete(String code) {
		return true;
	}

	@Override
	public List<ProductDto> getAll() {
		return TestUtil.createProductDtoList();
	}

	@Override
	public void deleteAll() {		
		
	}
	

}
