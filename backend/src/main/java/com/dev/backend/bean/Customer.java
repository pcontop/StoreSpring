package com.dev.backend.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.dev.beans.CustomerDto;

/**
 * Entity bean for the Customer. It has convenience methods to convert itself from/to CustomerDto.
 * @author pcont_000
 *
 */
@Entity
public class Customer {
	@Id
	private String code;
	private String name;
	private String address;
	private String phone1;
	private String phone2;
	private Double creditLimit;
	private Double currentCredit;
	
	private Customer(){}
	
	private Customer(String code){
		this.code = code;
	}
			
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getPhone1() {
		return phone1;
	}
	public Double getCurrentCredit() {
		return currentCredit;
	}
			
	public String getAddress() {
		return address;
	}

	public String getPhone2() {
		return phone2;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	private void setCode(String code) {
		this.code = code;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setAddress(String address) {
		this.address = address;
	}

	private void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	private void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	private void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	private void setCurrentCredit(Double currentCredit) {
		this.currentCredit = currentCredit;
	}

	/**
	 * Generates a customer Entity object from a CustomerDto object.
	 * @param customerDto
	 * @return a Customer object.
	 */
	public static Customer fromDto(CustomerDto customerDto){
		Customer customer = Customer.create()
				.setCode(customerDto.getCode())
				.setName(customerDto.getName())
				.setAddress(customerDto.getAddress())
				.setPhone1(customerDto.getPhone1())
				.setPhone2(customerDto.getPhone2())
				.setCreditLimit(customerDto.getCreditLimit())
				.setCurrentCredit(customerDto.getCurrentCredit())
				.build();
		return customer;
	}
	
	/**
	 * Converts the instance of the Customer object to an immutable Dto object.
	 * Note: Subsequent modifications to the Customer object will not be passed on 
	 * to a formerly created Dto object! Create only when needed!
	 * @return The Customer Dto with the data. 
	 */
	public CustomerDto toDto(){
		return CustomerDto.create()
				.setCode(this.getCode())
				.setName(this.getName())
				.setAddress(this.getAddress())
				.setPhone1(this.getPhone1())
				.setPhone2(this.getPhone2())
				.setCreditLimit(this.getCreditLimit())
				.setCurrentCredit(this.getCurrentCredit())
				.build();
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
		Customer other = (Customer) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	protected static Builder create(){
		return new Builder();
	}
	
	protected static class Builder {
		private Customer customer = new Customer();
		
		public Builder setCode(String code) {
			customer.setCode(code);
			return this;
		}
		public Builder setName(String name) {
			customer.setName(name);
			return this;
		}
		public Builder setPhone1(String phone1) {
			customer.setPhone1(phone1);
			return this;
		}
		public Builder setCurrentCredit(Double currentCredit) {
			customer.setCurrentCredit(currentCredit);
			return this;
		}
		public Builder setAddress(String address) {
			customer.setAddress(address);
			return this;
		}
		public Builder setPhone2(String phone2) {
			customer.setPhone2(phone2);
			return this;
		}
		public Builder setCreditLimit(Double creditLimit) {
			customer.setCreditLimit(creditLimit);
			return this;
		}
		
		public Customer build(){
			return customer;
		}
	}

	@Override
	public String toString() {
		return "Customer [code=" + code + ", name=" + name + ", address=" + address + ", phone1=" + phone1 + ", phone2="
				+ phone2 + ", creditLimit=" + creditLimit + ", currentCredit=" + currentCredit + "]";
	}

	/**
	 * Adds the value to the customer's credit expense. Positive values add to the customer's debt, negative
	 * values reduce it.  
	 * @param priceOrder The value to be added. 
	 */
	public void addCredit(double priceOrder) {
		this.setCurrentCredit(getCurrentCredit() + priceOrder);
		
	}
					
}
