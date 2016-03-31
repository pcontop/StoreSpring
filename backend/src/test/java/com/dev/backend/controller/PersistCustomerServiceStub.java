package com.dev.backend.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dev.backend.bean.TestUtil;
import com.dev.backend.service.PersistService;
import com.dev.beans.CustomerDto;

@Service @Primary
class PersistCustomerServiceStub implements PersistService<CustomerDto>{

	@Override
	public boolean save(CustomerDto customer) {
		return true;
	}

	@Override
	public CustomerDto get(String code) {
		return CustomerDto.create().setCode(code).build();
	}
	
	@Override
	public boolean delete(String code) {
		return true;
	}

	@Override
	public List<CustomerDto> getAll() {
		return TestUtil.createCustomerDtoList();
	}

	@Override
	public void deleteAll() {		
		
	}
	

}
