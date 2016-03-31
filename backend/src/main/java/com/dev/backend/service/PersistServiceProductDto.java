package com.dev.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.bean.Product;
import com.dev.backend.dao.ProductDao;
import com.dev.beans.ProductDto;

/**
 * Implements the CRUD services to be done with the Product. 
 * This Services will persist and retrieve all data using the injected instance of ProductDao.    
 * @author pcont_000
 */
@Service
public class PersistServiceProductDto implements PersistService<ProductDto>{
	@Autowired
	ProductDao productDao;

	/**
	 * Saves or updates a product.
	 */
	@Override
	public boolean save(ProductDto productDto) {
		productDao.save(Product.fromDto(productDto));
		return true;
	}

	/**
	 * Returns a productDto that has the supplied code.
	 * @param code The product code.
	 * @return The productDto, or null if not found.
	 */
	@Override
	public ProductDto get(String code) {
		Product product = productDao.findOne(code);
		if (product==null){
			return null;
		}
		return product.toDto();
	}

	/**
	 * Deletes a Product. 
	 * @param The product code.
	 * @return true If the product could be removed, false otherwise. 
	 */
	@Override
	public boolean delete(String code) {
		productDao.delete(code);
		return productDao.findOne(code)==null;
	}

	/**
	 * Returns a list of productDtos of all persisted products.
	 */
	@Override
	public List<ProductDto> getAll() {
		Iterable<Product> products = productDao.findAll();
		List<ProductDto> productDtos = new ArrayList<>();
		for (Product product: products){
			productDtos.add(product.toDto());
		}
		return productDtos;
	}

	/**
	 * Tries to delete all products. This is used only for testing, it *does not* check dependencies.
	 */
	@Override
	public void deleteAll() {		
		productDao.deleteAll();
	}
	

}
