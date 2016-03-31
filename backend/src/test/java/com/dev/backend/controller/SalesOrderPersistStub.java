package com.dev.backend.controller;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dev.backend.bean.TestUtil;
import com.dev.backend.service.PersistService;
import com.dev.beans.SalesOrderDto;

@Service @Primary
class SalesOrderPersistStub implements PersistService<SalesOrderDto>{

	@Override
	public boolean save(SalesOrderDto productDto) {
		return true;
	}

	@Override
	public SalesOrderDto get(String code) {
		return SalesOrderDto.create().setOrderNum(code).build();
	}
	
	@Override
	public boolean delete(String code) {
		return true;
	}

	@Override
	public List<SalesOrderDto> getAll() {
		return TestUtil.createSalesOrderDtoList();
	}

	@Override
	public void deleteAll() {		
		
	}
	

}
