package com.dev.backend.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dao.ProductDao;
import com.dev.beans.ProductOrderDto;

/**
 * Preferred factory to build a ProductOrder entity object from a Dto object.
 *  
 * @author pcont_000
 *
 */
@Service
public class ProductOrderFactory {
	@Autowired
	private ProductDao productDao;

	/**
	 * Builds a ProductOrder object from a ProductOrderDto object.
	 * 
	 *  Accesses the productDao to retrieve the Product information 
	 *  that was referred on the Dto object. If the product data has not been found, 
	 *  returns null.
	 *   
	 * @param productOrderDto
	 * @return
	 */
	public ProductOrder buildFromDto(ProductOrderDto productOrderDto) {
		Product product = productDao.findOne(productOrderDto.getProductCode());
		if (product==null){
			return null;
		}
		return ProductOrder.fromDto(productOrderDto, product);
	}
	

}
