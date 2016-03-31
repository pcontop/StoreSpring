package com.dev.backend.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import com.dev.backend.BackendApplication;
import com.dev.backend.bean.Product;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.service.PersistServiceCustomerDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BackendApplication.class, PersistServiceCustomerDto.class})
@WebAppConfiguration
public class ProductDaoUnitTest implements CrudDaoTest {
	@Autowired
	private SalesOrderDao salesOrderDao;

	@Autowired
	private ProductDao productDao;
	
	@Autowired 
	ProductOrderDao productOrderDao;
	
	@Before
	public void setup (){
		salesOrderDao.deleteAll();
		productOrderDao.deleteAll();
		productDao.deleteAll();
		productDao.save(TestUtil.createProduct1());
	}
	
	@Test
	public void testWorks(){}
	

	@Test @Override
	public void testGet(){
		Product created = TestUtil.createProduct1();
		Product product = productDao.findOne(created.getCode());
		assertNotNull(product);
		TestUtil.compare(product, created);
	}
	
	@Test @Override
	public void testInsert(){
		Product newProduct = TestUtil.createProduct2();
		assertNull(productDao.findOne(newProduct.getCode()));
		productDao.save(newProduct);
		Product productSaved = productDao.findOne(newProduct.getCode());
		TestUtil.compare(newProduct, productSaved);
	}
	
	@Test @Override
	public void testDelete(){
		Product newProduct = TestUtil.createProduct2();
		assertNull(productDao.findOne(newProduct.getCode()));
		productDao.save(newProduct);
		assertNotNull(productDao.findOne(newProduct.getCode()));		
		productDao.delete(newProduct);
		assertNull(productDao.findOne(newProduct.getCode()));
	}

	
	@Test @Override
	public void testGetAll(){
		testInsert();
		Iterable<Product> products = productDao.findAll();
		assertNotNull(products);
		int i=0;
		for (Product product: products){
			assertNotNull(product);
			i++;
		}
		assertEquals(i,2);
	}
	
	@Test @Override
	public void testUpdate() {
		Product created = TestUtil.createProduct1();
		Product found = productDao.findOne(created.getCode());
		TestUtil.compare(found, created);
		Product altered = TestUtil.createProduct1Modified();
		productDao.save(altered);
		Product updateResult = productDao.findOne(created.getCode());
		assertNotEquals(updateResult.getDescription(), found.getDescription());
		TestUtil.compare(altered, updateResult);				
	}	
	
	@After
	public void cleanup(){
		productDao.deleteAll();
	}
}
