package com.dev.backend.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dev.backend.bean.Customer;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.dao.CustomerDao;
import com.dev.backend.service.PersistService;
import com.dev.beans.CustomerDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CustomerServiceConfig.class})
@WebAppConfiguration
public class CustomerServiceUnitTest {
	@Autowired
	private PersistService<CustomerDto> customerService;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Before
	public void setup (){
		((CustomerDaoStub) customerDao).reset();
	}
	
	@Test
	public void testWorks(){}
	

	@Test
	public void testGet(){
		CustomerDto created = TestUtil.createCustomerDto1();
		CustomerDto customer = customerService.get(created.getCode());
		assertNotNull(customer);
		TestUtil.compare(customer, created);
	}

	@Test
	public void testPut(){
		Customer newCustomer = TestUtil.createCustomer3();
		assertNull(customerDao.findOne(newCustomer.getCode()));
		customerDao.save(newCustomer);
		Customer customerSaved = customerDao.findOne(newCustomer.getCode());
		TestUtil.compare(newCustomer, customerSaved);
	}
	
	@Test
	public void testDelete(){
		Customer existingCustomer = TestUtil.createCustomer1();
		assertNotNull(customerDao.findOne(existingCustomer.getCode()));
		customerDao.delete(existingCustomer.getCode());
		assertNull(customerDao.findOne(existingCustomer.getCode()));
	}

	@After
	public void cleanup(){
		customerDao.deleteAll();
	}
}
