package com.dev.beans;

/**
 * Object that acts as a container/DTO for the Product. 
 * @author pcont_000
 *
 */
public class ProductDto {
	private String code;
	private String description;
	private Integer quantity;
	private Double price;
	
	private ProductDto(){
		
	}
	
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
		ProductDto other = (ProductDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public static class Builder{
		ProductDto productDto = new ProductDto();

		public Builder setCode(String code) {
			productDto.setCode(code);
			return this;
		}
		public Builder setDescription(String description) {
			productDto.setDescription(description);
			return this;
		}
		public Builder setQuantity(Integer quantity) {
			productDto.setQuantity(quantity);
			return this;
		}
		public Builder setPrice(Double price) {
			productDto.setPrice(price);
			return this;
		}
		public ProductDto build(){
			return productDto;
		}
		
	}

	@Override
	public String toString() {
		return "ProductDto [code=" + code + ", description=" + description + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
	
	
	

}
