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
import com.dev.beans.ProductDto;

/**
 * Controller containing the mapping for the necessary CRUD Rest methods for the Products. It is located at 
 * the <base_server>/product uri directory. 
 * 
 *  The controller repasses all the CRUD operations to an indicated implementation of PersistService<ProductDto>.
 *   
 * @author pcont_000
 *
 */
@RestController
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	PersistService<ProductDto> persistCustomer;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping(){
		return "ok";
	}

	/**
	 * Get method. Returns a ProductDto of the persisted entity defined by the product code 
	 * passed as a path parameter. The Dto is returned as a json body object.
	 * @param Product code, or null
	 * @return ProductDto
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ProductDto get(@PathVariable(value="code") String code){
		return persistCustomer.get(code);
	}

	/**
	 * Post method. Persists the ProductDto, and if successful, returns the same ProductDto 
	 * as a json body object.
	 * @param product ProductDto object to be persisted.
	 * @return ProduceDto, or null. 
	 */
	@Transactional
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProductDto save(@RequestBody ProductDto product){
		System.out.println("Received a order to persist: " + product);		
		if (persistCustomer.save(product)){
			return product;
		}
		return null;
	}
	
	/**
	 * Delete method. Deletes the Product represented by the code on the pathVariable. 
	 * Returns true as a jsonObject if successful, false otherwise.
	 * @param code Product code.
	 * @return True or False (json)
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public Boolean deleteByCode(@PathVariable(value="code") String code){
		return persistCustomer.delete(code);
	}

	/**
	 * Get method. Returns a list of ProductDto objects of all the persisted customer entities.
	 * @return List of productDtos for all products.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<ProductDto> getAll(){
		return persistCustomer.getAll();
	}

}
