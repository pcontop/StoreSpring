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
import com.dev.backend.bean.SalesOrder;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.service.PersistServiceSalesOrderDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BackendApplication.class, PersistServiceSalesOrderDto.class})
@WebAppConfiguration
public class SalesOrderDaoUnitTest implements CrudDaoTest {
	@Autowired
	private SalesOrderDao salesOrderDao;
	@Autowired 
	private CustomerDao customerDao;
	@Autowired 
	private ProductDao productDao;
	
	@Before
	public void setup (){
		salesOrderDao.deleteAll();
		//Creating dependencies.
		customerDao.save(TestUtil.createCustomer1());
		productDao.save(TestUtil.createProduct1());
		//Initiating the sale order.
		salesOrderDao.save(TestUtil.createSalesOrder1());
	}
	
	@Test
	public void testWorks(){}
	
	@Test @Override
	public void testGet(){
		SalesOrder created = TestUtil.createSalesOrder1();
		SalesOrder found = salesOrderDao.findOne(created.getOrderNum());
		assertNotNull(found);
		TestUtil.compare(found, created);
	}
	
	@Test @Override
	public void testInsert(){
		SalesOrder newOrder = TestUtil.createSalesOrder2();
		assertNull(salesOrderDao.findOne(newOrder.getOrderNum()));
		
		customerDao.save(TestUtil.createCustomer2());
		productDao.save(TestUtil.createProduct2());
		
		salesOrderDao.save(newOrder);
		SalesOrder orderSaved = salesOrderDao.findOne(newOrder.getOrderNum());
		TestUtil.compare(newOrder, orderSaved);
	}
	
	@Test @Override
	public void testDelete() {
		SalesOrder existingOrder = TestUtil.createSalesOrder1();
		assertNotNull(salesOrderDao.findOne(existingOrder.getOrderNum()));
		salesOrderDao.delete(existingOrder.getOrderNum());
				
		assertNull(salesOrderDao.findOne(existingOrder.getOrderNum()));

	}
	
	@Test @Override
	public void testGetAll(){
		testInsert();
		Iterable<SalesOrder> salesOrders = salesOrderDao.findAll();
		assertNotNull(salesOrders);
		int i=0;
		for (SalesOrder salesOrder: salesOrders){
			assertNotNull(salesOrder);
			i++;
		}
		assertEquals(i,2);
	}
	
	@Test @Override
	public void testUpdate() {
		//Pre-setting the soon-to-be needed product and customer.
		productDao.save(TestUtil.createProduct3());		
		customerDao.save(TestUtil.createCustomer2());
		SalesOrder created = TestUtil.createSalesOrder1();
		SalesOrder found = salesOrderDao.findOne(created.getOrderNum());
		TestUtil.compare(found, created);
		SalesOrder altered = TestUtil.createSalesOrder1Modified();
		salesOrderDao.save(altered);
		SalesOrder updateResult = salesOrderDao.findOne(created.getOrderNum());
		assertNotEquals(updateResult.getCustomer(), found.getCustomer());
		TestUtil.compare(altered, updateResult);				
	}	
	
	
	@After
	public void cleanup(){
		salesOrderDao.deleteAll();		
		customerDao.deleteAll();
		productDao.deleteAll();
	}

}
