package com.dev.backend.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dev.backend.dao.CustomerDao;
import com.dev.backend.service.PersistServiceCustomerDto;
import com.dev.backend.service.PersistService;
import com.dev.beans.CustomerDto;

@Configuration
public class CustomerServiceConfig {
	@Bean
	CustomerDao getCustomerDao(){
		return new CustomerDaoStub();
	}
	
	@Bean
	PersistService<CustomerDto> getPersistService(){
		return new PersistServiceCustomerDto();
	}
}
