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
import com.dev.beans.CustomerDto;

/**
 * Controller containing the mapping for the necessary CRUD Rest methods for the Customers. It is located at 
 * the <base_server>/customer uri directory. 
 * 
 *  The controller repasses all the CRUD operations to an indicated implementation of PersistService<CustomerDto>.
 *   
 * @author pcont_000
 *
 */
@RestController
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	PersistService<CustomerDto> persistCustomer;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)	
	public String ping(){
		return "ok";
	}

	
	/**
	 * Get method. Returns a CustomerDto of the persisted entity defined by the customer code 
	 * passed as a path parameter. The Dto is returned as a json body object.
	 * @param Customer code
	 * @return A CustumerDto object, or null.
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public CustomerDto get(@PathVariable(value="code") String customerCode){
		return persistCustomer.get(customerCode);
	}

	/**
	 * Post method. Persists the CustomerDto, and if successful, returns the same CustomerDto 
	 * as a json body object.
	 * @param customer CustomerDto object to be persisted. 
	 * @return CustomerDto, or null.
	 */
	@Transactional
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public CustomerDto save(@RequestBody CustomerDto customer){
		System.out.println("Received a order to persist: " + customer);
		if (persistCustomer.save(customer)) {
			return customer;
		}
		return null;
	}
	
	/**
	 * Delete method. Deletes the customer represented by the code on the pathVariable. 
	 * Returns true as a jsonObject if successful, false otherwise.
	 * @param code Customer code.
	 * @return True or False (json)
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public Boolean deleteByCode(@PathVariable(value="code") String customerCode){
		return persistCustomer.delete(customerCode);
	}
	
	/**
	 * Get method. Returns a list of CustomerDto objects of all the persisted customer entities.
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<CustomerDto> getAll(){
		return persistCustomer.getAll();
	}

}
