package com.dev.backend.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dev.backend.BackendApplication;
import com.dev.backend.bean.Customer;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.service.PersistServiceCustomerDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BackendApplication.class, PersistServiceCustomerDto.class})
@WebAppConfiguration
public class CustomerDaoUnitTest implements CrudDaoTest {
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Before
	public void setup (){
		salesOrderDao.deleteAll();
		customerDao.deleteAll();
		Customer customer = TestUtil.createCustomer1();
		customerDao.save(customer);
	}
	
	@Test
	public void testWorks(){}
	

	@Test @Override
	public void testGet(){
		Customer created = TestUtil.createCustomer1();
		Customer customer = customerDao.findOne(created.getCode());
		assertNotNull(customer);
		TestUtil.compare(created, customer);
	}
	
	@Test @Override
	public void testInsert(){
		Customer newCustomer = TestUtil.createCustomer2();
		assertNull(customerDao.findOne(newCustomer.getCode()));
		customerDao.save(newCustomer);
		Customer customerSaved = customerDao.findOne(newCustomer.getCode());
		TestUtil.compare(newCustomer, customerSaved);
	}
	
	@Test @Override
	public void testDelete(){
		Customer newCustomer = TestUtil.createCustomer2();
		assertNull(customerDao.findOne(newCustomer.getCode()));
		customerDao.save(newCustomer);
		assertNotNull(customerDao.findOne(newCustomer.getCode()));		
		customerDao.delete(newCustomer);
		assertNull(customerDao.findOne(newCustomer.getCode()));
	}

	
	@Test @Override
	public void testGetAll(){
		testInsert();
		Iterable<Customer> customers = customerDao.findAll();
		assertNotNull(customers);
		int i=0;
		for (Customer customer: customers){
			assertNotNull(customer);
			i++;
		}
		assertEquals(i,2);
	}
	
	@After
	public void cleanup(){
		customerDao.deleteAll();
	}

	@Test @Override
	public void testUpdate() {
		Customer created = TestUtil.createCustomer1();
		Customer customer = customerDao.findOne(created.getCode());
		TestUtil.compare(customer, created);
		Customer altered = TestUtil.createCustomer1Modified();
		customerDao.save(altered);
		Customer updateResult = customerDao.findOne(created.getCode());
		assertNotEquals(updateResult.getName(), customer.getName());
		TestUtil.compare(altered, updateResult);				
	}
}
