package com.dev.beans;

/**
 * Object that acts as a container/DTO for the productOrders. 
 * 
 * Will contain a reference to the Product. The price is there only as a convenience, as
 * the backend's price will be used on operations, to reduce wrongdoings.   
 *   
 * @author pcont_000
 *
 */
public class ProductOrderDto {
	String productCode;
	Integer productQuantity;
	Double productPrice;
	
	private ProductOrderDto(){ }
		
	public String getProductCode() {
		return productCode;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	
	public Double getTotalPrice() {
		return productPrice * productQuantity;
	}
	private void setCode(String code) {
		this.productCode = code;
	}
	private void setQuantity(Integer quantity) {
		this.productQuantity = quantity;
	}
	private void setPrice(Double price) {
		this.productPrice = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		result = prime * result + ((productQuantity == null) ? 0 : productQuantity.hashCode());
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
		ProductOrderDto other = (ProductOrderDto) obj;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productQuantity == null) {
			if (other.productQuantity != null)
				return false;
		} else if (!productQuantity.equals(other.productQuantity))
			return false;
		return true;
	}
	
	public static Builder create(){
		return new Builder();
	}
	
	public static class Builder{
		ProductOrderDto productOrderDto = new ProductOrderDto();
		
		public Builder setCode(String code) {
			productOrderDto.setCode(code);
			return this;
		}
		public Builder setQuantity(Integer quantity) {
			productOrderDto.setQuantity(quantity);
			return this;
		}
		public Builder setPrice(Double price) {
			productOrderDto.setPrice(price);
			return this;
		}
		
		public ProductOrderDto build(){
			return productOrderDto;
		}
		
	}

	@Override
	public String toString() {
		return "ProductOrderDto [code=" + productCode + ", quantity=" + productQuantity + ", price=" + productPrice + "]";
	}

		
}
