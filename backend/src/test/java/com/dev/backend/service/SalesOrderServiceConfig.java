package com.dev.backend.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dev.backend.bean.ProductOrderFactory;
import com.dev.backend.bean.SalesOrderFactory;
import com.dev.backend.dao.CustomerDao;
import com.dev.backend.dao.ProductDao;
import com.dev.backend.dao.SalesOrderDao;
import com.dev.beans.SalesOrderDto;

@Configuration
public class SalesOrderServiceConfig {
	@Bean
	SalesOrderDao getSalesOrderDao(){
		return new SalesOrderDaoStub();
	}

	@Bean
	CustomerDao getCustumerDao(){
		return new CustomerDaoStub();
	}

	@Bean
	ProductDao getProductDao(){
		return new ProductDaoStub();
	}

	@Bean
	PersistService<SalesOrderDto> getPersistService(){
		return new PersistServiceSalesOrderDto();
	}

	@Bean
	SalesOrderFactory getSalesOrderFactory(){
		return new SalesOrderFactory();
	}

	@Bean
	ProductOrderFactory getProductOrderFactory(){
		return new ProductOrderFactory();
	}

}
