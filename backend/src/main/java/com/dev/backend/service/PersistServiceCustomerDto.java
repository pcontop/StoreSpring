package com.dev.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.bean.Customer;
import com.dev.backend.dao.CustomerDao;
import com.dev.beans.CustomerDto;

/**
 * Implements the CRUD services to be done with the Customer.
 * This Services will persist and retrieve all data using the injected instance of CustomerDao.    
 * @author pcont_000
 */
@Service
public class PersistServiceCustomerDto implements PersistService<CustomerDto>{
	@Autowired
	CustomerDao customerDao;

	
	/**
	 * Saves or updates a customer with the passed customerDto.
	 * @param The customerDto with the new customer data.
	 * @return If the operation has been successful. 
	 */
	@Override
	public boolean save(CustomerDto customerDto) {
		customerDao.save(Customer.fromDto(customerDto));
		return true;
	}

	/**
	 * Retrieves a customerDto with the passed code.
	 * @param The customer code.
	 * @param The customer, or null if not found. 
	 */
	@Override
	public CustomerDto get(String code) {
		Customer customer = customerDao.findOne(code);
		if (customer==null){
			return null;
		}
		return customer.toDto();
	}

	/**
	 * Deletes a customer with the code passed.
	 * @return true if removed with success, false if not. 
	 */
	@Override
	public boolean delete(String code) {
		customerDao.delete(code);
		return customerDao.findOne(code)==null;
	}

	/**
	 * Returns a list of CustomerDto of all customer persisted. 
	 */
	@Override
	public List<CustomerDto> getAll() {
		Iterable<Customer> customers = customerDao.findAll();
		List<CustomerDto> customerDtos = new ArrayList<>();
		for (Customer customer: customers){
			customerDtos.add(customer.toDto());
		}
		return customerDtos;
	}

	/**
	 * Deletes all the users. Used only for test purposes! It *does not* check 
	 * for failed removals due to dependencies. 
	 */
	@Override
	public void deleteAll() {		
		customerDao.deleteAll();
	}
	

}
