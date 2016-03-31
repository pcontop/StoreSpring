package com.dev.backend.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dev.beans.SalesOrderDto;

/**
 * Entity bean for the SalesOrder. It has convenience methods to convert itself from/to SalesOrderDto.
 * 
 * This object should only be instantiated by the SalesOrderFactory. There is a protected constructor here, 
 * but it should be used internally, or for test purposes only. 
 * 
 * @see SalesOrderFactory
 * @author pcont_000
 *
 */
@Entity
public class SalesOrder {
	@Id
	private String orderNum;
	@ManyToOne
	private Customer customer;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private List<ProductOrder> productOrders;
	
	private SalesOrder(){}

	public String getOrderNum() {
		return orderNum;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	private void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	private void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	private void setProductOrders(List<ProductOrder> products) {
		this.productOrders = products;
	}

	protected static Builder create(){
		return new Builder();
	}

	protected static class Builder{
		SalesOrder salesOrder = new SalesOrder();
		
		public Builder setOrderNum(String orderNum) {
			salesOrder.setOrderNum(orderNum);
			return this;
		}

		public Builder setCustomer(Customer customer) {
			salesOrder.setCustomer(customer);
			return this;
		}

		public Builder setProductOrders(List<ProductOrder> products) {
			salesOrder.setProductOrders(products);
			return this;
		}
		
		public SalesOrder build(){
			return salesOrder;
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderNum == null) ? 0 : orderNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesOrder other = (SalesOrder) obj;
		if (orderNum == null) {
			if (other.orderNum != null)
				return false;
		} else if (!orderNum.equals(other.orderNum))
			return false;
		return true;
	}
	
	/**
	 * Generates a SalesOrder entity object from a DalesOrderDto object, a customerObject,
	 * and a list of ProductOrder objects.
	 * @param salesOrderDto The original Dto.
	 * @param Customer the Customer entity object to be associated to the order.
	 * @param productOrders A list of all the ProductOrder entity objects that the order contains.
	 * @return a SalesOrder object.
	 */
	protected static SalesOrder fromDto(SalesOrderDto salesOrderDto, 
			Customer customer, 
			List<ProductOrder> productOrders){
		return SalesOrder.create()
				.setOrderNum(salesOrderDto.getOrderNum())
				.setCustomer(customer)
				.setProductOrders( productOrders)
				.build();
	}
	
	/**
	 * Return a SalesOrderDto object mapped from this entity instance. 
	 * @return The salesOrderDto mapping of this entity bean.
	 */
	public SalesOrderDto toDto(){
		return SalesOrderDto.create()
				.setCustomerCode(getCustomer().getCode())
				.setOrderNum(getOrderNum())
				.setCustomerName(getCustomer().getName())
				.setProductOrders(
						getProductOrders().stream()
						.map(c -> c.toDto())
						.collect(Collectors.toList())
						)
				.build();
	}

	@Override
	public String toString() {
		return "SalesOrder [orderNum=" + orderNum + ", customer=" + customer + ", productOrders=" + productOrders + "]";
	}
	
	
}
