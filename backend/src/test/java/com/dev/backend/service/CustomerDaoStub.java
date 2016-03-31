package com.dev.backend.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.backend.bean.Customer;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.dao.CustomerDao;

@Service
class CustomerDaoStub implements CustomerDao{
	
	private Map<String, Customer> customers;
	
	public CustomerDaoStub(){
		reset();
	}

	@Override
	public long count() {
		return customers.size();
	}

	@Override
	public void delete(String arg0) {
		customers.remove(arg0);		
	}

	@Override
	public void delete(Customer arg0) {
		customers.remove(arg0.getCode());		
	}

	@Override
	public void delete(Iterable<? extends Customer> arg0) {
		for (Customer customer: arg0){
			delete(customer);
		}
		
	}

	@Override
	public void deleteAll() {
		customers.clear();
		
	}

	@Override
	public boolean exists(String arg0) {
		return customers.containsKey(arg0);
	}

	@Override
	public Iterable<Customer> findAll() {
		return customers.values();
	}

	@Override
	public Iterable<Customer> findAll(Iterable<String> arg0) {
		Set<Customer> customerSet = new HashSet<>();
		for (String key: arg0){
			customerSet.add(customers.get(key));
		}
		return customerSet;
	}

	@Override
	public Customer findOne(String arg0) {
		return customers.get(arg0);
	}

	@Override
	public <S extends Customer> S save(S arg0) {
		System.out.println("Simulating save of:" + arg0);
		customers.put(arg0.getCode(), arg0);
		return arg0;
	}

	@Override
	public <S extends Customer> Iterable<S> save(Iterable<S> arg0) {
		for (Customer c: arg0){
			save(c);
		}
		return arg0;
	}

	public void reset() {
		customers = TestUtil.createCustomerList()
				.stream()
				.collect(Collectors.toMap(Customer::getCode,
                Function.identity()));				
	}

}
