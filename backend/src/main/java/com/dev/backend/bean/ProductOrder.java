package com.dev.backend.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dev.beans.ProductOrderDto;

/**
 * Entity bean for the ProductOrder. It has convenience methods to convert itself from/to ProductOrderDto.
 * 
 * This object should only be instantiated by the ProductOrderFactory. There is a protected constructor here, 
 * but it should be used internally, or for test purposes only. 
 * 
 * @see ProductOrderFactory
 * @author pcont_000
 *
 */
@Entity
public class ProductOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Product product;
	private Integer quantity;
	private Double price;
	@ManyToOne
	private SalesOrder salesOrder;
	
	private ProductOrder(){ }
		
	public Product getProduct() {
		return product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getPrice() {
		return price;
	}
	
	private void setProduct(Product product) {
		this.product = product;
	}
	private void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	private void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		ProductOrder other = (ProductOrder) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
	
	protected static Builder create(){
		return new Builder();
	}
	
	protected static class Builder{
		ProductOrder productOrder = new ProductOrder();
		
		public Builder setProduct(Product product) {
			productOrder.setProduct(product);
			return this;
		}
		public Builder setQuantity(Integer quantity) {
			productOrder.setQuantity(quantity);
			return this;
		}
		public Builder setPrice(Double price) {
			productOrder.setPrice(price);
			return this;
		}
		
		public ProductOrder build(){
			return productOrder;
		}
		
	}
	
	/**
	 * Generates a ProductOrder entity object from a ProductOrderDto object.
	 * @param productOrderDto The original Dto.
	 * @param Product the Product object to be associated to the order.
	 * @return a ProductOrder object.
	 */
	protected static ProductOrder fromDto(ProductOrderDto productOrderDto, Product product){
		return ProductOrder.create()
				.setProduct(product)
				.setPrice(productOrderDto.getProductPrice())
				.setQuantity(productOrderDto.getProductQuantity())
				.build();
	}
	
	/**
	 * Converts the instance of the ProductOrder object to an immutable Dto object.
	 * Note: Subsequent modifications to the ProductOrder object will not be passed on 
	 * to a formerly created Dto object! Create only when needed!
	 * @return The ProductOrder Dto with the data. 
	 */	public ProductOrderDto toDto(){
		return ProductOrderDto.create()
				.setCode(getProduct()==null?null:getProduct().getCode())
				.setPrice(getPrice())
				.setQuantity(getQuantity())
				.build();
	}

	@Override
	public String toString() {
		return "ProductOrder [product=" + product + ", quantity=" + quantity + ", price=" + price + "]";
	}
		

}
