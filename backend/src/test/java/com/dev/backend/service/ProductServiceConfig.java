package com.dev.backend.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dev.backend.dao.ProductDao;
import com.dev.beans.ProductDto;

@Configuration
public class ProductServiceConfig {
	@Bean
	ProductDao getProductDao(){
		return new ProductDaoStub();
	}
	
	@Bean
	PersistService<ProductDto> getPersistService(){
		return new PersistServiceProductDto();
	}
}
