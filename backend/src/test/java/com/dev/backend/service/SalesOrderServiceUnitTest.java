package com.dev.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dev.backend.bean.Customer;
import com.dev.backend.bean.Product;
import com.dev.backend.bean.SalesOrder;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.dao.CustomerDao;
import com.dev.backend.dao.ProductDao;
import com.dev.backend.dao.SalesOrderDao;
import com.dev.beans.SalesOrderDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SalesOrderServiceConfig.class})
@WebAppConfiguration
public class SalesOrderServiceUnitTest {
	@Autowired
	private PersistService<SalesOrderDto> salesOrderService;
	
	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;

	@Before
	public void setup (){
		((SalesOrderDaoStub) salesOrderDao).reset();
		((ProductDaoStub)productDao).reset();
		((CustomerDaoStub)customerDao).reset();
	}
	
	@Test
	public void testWorks(){}
	
	@Test
	public void testPutMoreThanStock(){
		SalesOrderDto salesOrder = TestUtil.createSalesOrderMoreThanStock();
		try {
			salesOrderService.save(salesOrder);
			assertTrue(false);
		} catch(IllegalArgumentException expected){
			expected.printStackTrace();			
		}
	}

	@Test
	public void testSubtractStock(){
		SalesOrderDto salesOrder = TestUtil.createSalesOrderDto1();
		assertTrue(salesOrderService.save(salesOrder));
		SalesOrderDto salesOrder2 = TestUtil.createSalesOrderDtoOtherCode1();
		try {
			salesOrderService.save(salesOrder2);
			assertTrue(false);
		} catch(IllegalArgumentException expected){
			expected.printStackTrace();			
		}
	}
	
	@Test
	public void testSubtractCredit(){
		SalesOrderDto salesOrder = TestUtil.createSalesOrderDto1();
		assertTrue(salesOrderService.save(salesOrder));
		SalesOrderDto salesOrder2 = TestUtil.createSalesOrderDtoOtherCode2();
		try {
			salesOrderService.save(salesOrder2);
			assertTrue(false);
		} catch(IllegalArgumentException expected){
			expected.printStackTrace();			
		}			
	}

	@Test
	public void testAlterOrder(){
		SalesOrderDto salesOrder = TestUtil.createSalesOrderDto1();
		assertTrue(salesOrderService.save(salesOrder));
		assertTrue(salesOrderService.save(salesOrder));								
		assertTrue(salesOrderService.save(salesOrder));								
	}

	@Test
	public void testPutMoreThanCredit(){
		SalesOrderDto salesOrder = TestUtil.createSalesOrderMoreThanCredit();
		try {
			salesOrderService.save(salesOrder);
			assertTrue(false);
		} catch (IllegalArgumentException expected){
			expected.printStackTrace();
		}
	}

	@Test
	public void testGet(){
		SalesOrderDto created = TestUtil.createSalesOrderDto1();
		SalesOrderDto found = salesOrderService.get(created.getOrderNum());
		assertNotNull(found);
		TestUtil.compare(found, created);
	}
	@Test
	public void testPut(){
		SalesOrder newOrder = TestUtil.createSalesOrder3();
		assertNull(salesOrderDao.findOne(newOrder.getOrderNum()));
		salesOrderDao.save(newOrder);
		SalesOrder salesOrderSaved = salesOrderDao.findOne(newOrder.getOrderNum());
		TestUtil.compare(newOrder, salesOrderSaved);
	}
	
	/**
	 * Checks if the order has been deleted, and then, if the credit has been refunded to the customer,
	 * and if the product quantities are refunded to the stock. 
	 */
	@Test
	public void testDeleteOrder(){
		SalesOrder existingOrder = TestUtil.createSalesOrder1();
		Double customerStartingCredit = existingOrder.getCustomer().getCurrentCredit();
		Product startingProduct = existingOrder.getProductOrders().get(0).getProduct();
		Integer productStartingQuantity = startingProduct.getQuantity();
		salesOrderService.delete(existingOrder.getOrderNum());
		assertNull(salesOrderDao.findOne(existingOrder.getOrderNum()));
		Customer customerRetrieved = customerDao.findOne(existingOrder.getCustomer().getCode());
		Double customerUpdatedCredit = customerRetrieved.getCurrentCredit();
		assertTrue(customerStartingCredit> customerUpdatedCredit);
		Product productRetrieved = productDao.findOne(startingProduct.getCode());
		Integer productUpdatedQuantity = productRetrieved.getQuantity();
		assertTrue(productUpdatedQuantity > productStartingQuantity);
	}
	
	@After
	public void cleanup(){
		salesOrderDao.deleteAll();
	}
}
