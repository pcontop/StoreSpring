package com.dev.beans;

import java.util.List;

/**
 * Object that acts as a container/DTO for the SalesOrders. 
 * 
 * Will contain all the ProductOrders from the SalesOrder, and a reference to the Customer. 
 *  
 * @author pcont_000
 *
 */
public class SalesOrderDto {
	private String orderNum;
	private String customerCode;
	private String customerName;
	private List<ProductOrderDto> productOrders;
	
	private SalesOrderDto(){}

	public String getOrderNum() {
		return orderNum;
	}

	public String getCustomerCode() {
		return customerCode;
	}
	
	public List<ProductOrderDto> getProductOrders() {
		return productOrders;
	}

	private void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	private void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	private void setProductOrders(List<ProductOrderDto> products) {
		this.productOrders = products;
	}		

	public String getCustomerName() {
		return customerName;
	}

	public Double getTotalPrice() {
		if (getProductOrders()==null) {
			return 0D;
		}
		return getProductOrders().stream()
				.mapToDouble(p -> p.getProductPrice() * p.getProductQuantity())
				.sum();
	}

	private void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public static Builder create(){
		return new Builder();
	}

	public static class Builder{
		SalesOrderDto salesOrderDto = new SalesOrderDto();
		
		public Builder setOrderNum(String orderNum) {
			salesOrderDto.setOrderNum(orderNum);
			return this;
		}

		public Builder setCustomerCode(String customerCode) {
			salesOrderDto.setCustomerCode(customerCode);
			return this;
		}

		public Builder setProductOrders(List<ProductOrderDto> products) {
			salesOrderDto.setProductOrders(products);
			return this;
		}
		
		public Builder setCustomerName(String customerName) {
			salesOrderDto.setCustomerName(customerName);
			return this;
		}
		
		
		public SalesOrderDto build(){
			return salesOrderDto;
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
		SalesOrderDto other = (SalesOrderDto) obj;
		if (orderNum == null) {
			if (other.orderNum != null)
				return false;
		} else if (!orderNum.equals(other.orderNum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesOrderDto [orderNum=" + orderNum + ", customerCode=" + customerCode + ", customerName="
				+ customerName + ", productOrders=" + productOrders + ", totalPrice=" + getTotalPrice() + "]";
	}
		

}
