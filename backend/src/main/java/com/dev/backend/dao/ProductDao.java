package com.dev.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.dev.backend.bean.Product;

public interface ProductDao  extends CrudRepository<Product, String>{

}
