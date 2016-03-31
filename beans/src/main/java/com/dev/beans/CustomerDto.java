package com.dev.beans;

/**
 * Object that acts as a container/DTO for the customer. 
 * @author pcont_000
 *
 */
public class CustomerDto {
	private String code;
	private String name;
	private String address;
	private String phone1;
	private String phone2;
	private Double creditLimit;
	private Double currentCredit;
			
	public String getCode() {
		return code;
	}
	private void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public Double getCurrentCredit() {
		return currentCredit;
	}
	private void setCurrentCredit(Double currentCredit) {
		this.currentCredit = currentCredit;
	}

	
	public String getAddress() {
		return address;
	}
	public String getPhone1() {
		return phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public Double getCreditLimit() {
		return creditLimit;
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
		CustomerDto other = (CustomerDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	public static Builder create(){
		return new Builder();
	}
	
	public static class Builder {
		private CustomerDto customer = new CustomerDto();
		
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
		
		public CustomerDto build(){
			return customer;
		}
	}

	@Override
	public String toString() {
		return "CustomerDto [code=" + code + ", name=" + name + ", address=" + address + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", creditLimit=" + creditLimit + ", currentCredit=" + currentCredit + "]";
	}
	
	
			
}
