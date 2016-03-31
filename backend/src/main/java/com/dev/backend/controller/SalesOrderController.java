package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.service.PersistService;
import com.dev.beans.SalesOrderDto;

/**
 * Controller containing the mapping for the necessary CRUD Rest methods for the SalesOrders. It is located at 
 * the <base_server>/salesOrder uri directory. 
 * 
 *  The controller repasses all the CRUD operations to an indicated implementation 
 *  of PersistService<SalesOrderDto>.
 *   
 * @author pcont_000
 *
 */
@RestController
@RequestMapping("/salesOrder/*")
public class SalesOrderController {
	
	@Autowired
	PersistService<SalesOrderDto> persistSalesOrder;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping(){
		return "ok";
	}

	/**
	 * Get method. Returns a SalesOrderDto of the persisted entity defined by the order number 
	 * passed as a path parameter. The Dto is returned as a json body object.
	 * @param Order number
	 * @return A SalesOrderDto object, or null.
	 */
	@RequestMapping(value = "/{orderNum}", method = RequestMethod.GET)
	public SalesOrderDto get(@PathVariable(value="orderNum") String orderNum){
		return persistSalesOrder.get(orderNum);
	}

	/**
	 * Post method. Saves the SalesOrderDto, and if successful, returns the same SalesOrderDto 
	 * as a json body object.
	 * @param salesOrder The SalesOrderDto object to be persisted. 
	 * @return SalesOrderDto, or null.
	 */
	@Transactional
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public SalesOrderDto save(@RequestBody SalesOrderDto salesOrder){
		System.out.println("Received a order to persist: " + salesOrder);		
		if (persistSalesOrder.save(salesOrder)){
			return salesOrder;
		}
		return null;
	}
	
	/**
	 * Delete method. Deletes the SalesOrder represented by the order number on the pathVariable. 
	 * Returns true as a jsonObject if successful, false otherwise.
	 * @param orderNum the Order Number.
	 * @return True or False (json)
	 */
	@RequestMapping(value = "/{orderNum}", method = RequestMethod.DELETE)
	public Boolean deleteByCode(@PathVariable(value="orderNum") String orderNum){
		return persistSalesOrder.delete(orderNum);
	}
	
	/**
	 * Get method. Returns a list of SalesOrderDto objects of all the persisted sales order entities.
	 * @return
	 */	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<SalesOrderDto> getAll(){
		return persistSalesOrder.getAll();
	}

}
