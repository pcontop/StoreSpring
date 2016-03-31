package com.dev.backend.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.backend.bean.Product;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.dao.ProductDao;

@Service
class ProductDaoStub implements ProductDao{
	
	private Map<String, Product> products;
	
	public ProductDaoStub(){
		reset();
	}

	@Override
	public long count() {
		return products.size();
	}

	@Override
	public void delete(String arg0) {
		products.remove(arg0);		
	}

	@Override
	public void delete(Product arg0) {
		products.remove(arg0.getCode());		
	}

	@Override
	public void delete(Iterable<? extends Product> arg0) {
		for (Product product: arg0){
			delete(product);
		}
		
	}

	@Override
	public void deleteAll() {
		products.clear();
		
	}

	@Override
	public boolean exists(String arg0) {
		return products.containsKey(arg0);
	}

	@Override
	public Iterable<Product> findAll() {
		return products.values();
	}

	@Override
	public Iterable<Product> findAll(Iterable<String> arg0) {
		Set<Product> productSet = new HashSet<>();
		for (String key: arg0){
			productSet.add(products.get(key));
		}
		return productSet;
	}

	@Override
	public Product findOne(String arg0) {
		return products.get(arg0);
	}

	@Override
	public <S extends Product> S save(S arg0) {
		System.out.println("Simulating saving of:" + arg0);
		products.put(arg0.getCode(), arg0);
		return arg0;
	}

	@Override
	public <S extends Product> Iterable<S> save(Iterable<S> arg0) {
		for (Product p: arg0){
			save(p);
		}
		return arg0;
	}

	public void reset() {
		products = TestUtil.createProductList()
				.stream()
				.collect(Collectors.toMap(Product::getCode,
                Function.identity()));				
	}

}
