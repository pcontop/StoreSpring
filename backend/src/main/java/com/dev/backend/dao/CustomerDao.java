package com.dev.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.dev.backend.bean.Customer;

public interface CustomerDao  extends CrudRepository<Customer, String>{

}
