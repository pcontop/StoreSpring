package com.dev.backend.bean;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dao.CustomerDao;
import com.dev.beans.SalesOrderDto;

/**
 * This factory builds a SalesOrders objects, fetching information from the Daos.
*/
@Service
public class SalesOrderFactory {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductOrderFactory productOrderFactory;
	
	/**
	 * Returns a Sales order from the Dto, while fetching the Customer entity object from
	 * the customerDao implementations, and the ProductOrder entity beans from the 
	 * ProductOrderFactory implementation. 
	 * @param salesOrderDto
	 * @return
	 */
	public SalesOrder fromDto(SalesOrderDto salesOrderDto){
		Customer customer = customerDao.findOne(salesOrderDto.getCustomerCode());
		if (customer==null){
			return null;
		}
		List<ProductOrder> orders = salesOrderDto.getProductOrders().stream()
				.map(c -> productOrderFactory.buildFromDto(c))
				.filter(c -> c!=null)
				.collect(Collectors.toList());
		if (orders==null || orders.size()!=salesOrderDto.getProductOrders().size()){
			return null;
		}
		return SalesOrder.fromDto(salesOrderDto, customer, orders);
	}

}
