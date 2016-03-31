package com.dev.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dev.backend.bean.Product;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.dao.ProductDao;
import com.dev.beans.ProductDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ProductServiceConfig.class})
@WebAppConfiguration
public class ProductServiceUnitTest {
	@Autowired
	private PersistService<ProductDto> productService;
	
	@Autowired
	private ProductDao productDao;
	
	@Before
	public void setup (){
		((ProductDaoStub) productDao).reset();
	}
	
	@Test
	public void testWorks(){}
	

	@Test
	public void testGet(){
		ProductDto created = TestUtil.createProductDto1();
		ProductDto product = productService.get(created.getCode());
		assertNotNull(product);
		TestUtil.compare(product, created);
	}
	@Test
	public void testPut(){
		Product newProduct = TestUtil.createProduct3();
		assertNull(productDao.findOne(newProduct.getCode()));
		productDao.save(newProduct);
		Product productSaved = productDao.findOne(newProduct.getCode());
		TestUtil.compare(newProduct, productSaved);
	}
	
	@Test
	public void testDelete(){
		Product existingProduct = TestUtil.createProduct1();
		assertNotNull(productDao.findOne(existingProduct.getCode()));
		productDao.delete(existingProduct.getCode());
		assertNull(productDao.findOne(existingProduct.getCode()));
	}
	
	
	@After
	public void cleanup(){
		productDao.deleteAll();
	}
}
