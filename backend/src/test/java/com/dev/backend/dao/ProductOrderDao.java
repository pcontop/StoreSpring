package com.dev.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.dev.backend.bean.ProductOrder;

/**
 * Used for cleanup on the product dao test.
 * @author pcont_000
 *
 */
public interface ProductOrderDao  extends CrudRepository<ProductOrder, String>{

}
