package com.dev.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.dev.backend.bean.SalesOrder;

public interface SalesOrderDao  extends CrudRepository<SalesOrder, String>{

}
