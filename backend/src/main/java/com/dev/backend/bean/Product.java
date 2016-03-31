package com.dev.backend.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.dev.beans.ProductDto;

/**
 * Entity bean for the Product. It has convenience methods to convert itself from/to ProductDto.
 * @author pcont_000
 *
 */
@Entity
public class Product {
	@Id
	private String code;
	private String description;
	private Integer quantity;
	private Double price;

	public String getCode() {
		return code;
	}
	private void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	private void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	private void setPrice(Double price) {
		this.price = price;
	}
	
	public static Builder create(){
		return new Builder();
	}
	
	public static class Builder{
		Product product = new Product();

		public Builder setCode(String code) {
			product.setCode(code);
			return this;
		}
		public Builder setDescription(String description) {
			product.setDescription(description);
			return this;
		}
		public Builder setQuantity(Integer quantity) {
			product.setQuantity(quantity);
			return this;
		}
		public Builder setPrice(Double price) {
			product.setPrice(price);
			return this;
		}
		public Product build(){
			return product;
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Product other = (Product) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [code=" + code + ", description=" + description + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
	
	/**
	 * Generates a product entity object from a ProductDto object.
	 * @param productDto The originating Dto object.
	 * @return a Product object.
	 */
	public static Product fromDto(ProductDto productDto){
		return Product.create()
				.setCode(productDto.getCode())
				.setDescription(productDto.getDescription())
				.setPrice(productDto.getPrice())
				.setQuantity(productDto.getQuantity())
				.build();
	}
	
	/**
	 * Converts the instance of the Product object to an immutable Dto object.
	 * Note: Subsequent modifications to the Product object will not be passed on 
	 * to a formerly created Dto object! Create only when needed!
	 * @return The Product Dto with the data. 
	 */
	public ProductDto toDto(){
		return ProductDto.create()
				.setCode(getCode())
				.setDescription(getDescription())
				.setPrice(getPrice())
				.setQuantity(getQuantity())
				.build();
	}
	
	/**
	 * Reduces the stock from the Product. If you want to add to the stock, use negative values.  
	 * 
	 * @param quantity
	 */
	public void subtractQuantity(Integer quantity) {
		this.setQuantity(getQuantity()-quantity);		
	}

}
