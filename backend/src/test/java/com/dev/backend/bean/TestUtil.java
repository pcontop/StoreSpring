package com.dev.backend.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.dev.backend.bean.Customer;
import com.dev.backend.bean.Product;
import com.dev.backend.bean.ProductOrder;
import com.dev.backend.bean.SalesOrder;
import com.dev.beans.CustomerDto;
import com.dev.beans.ProductDto;
import com.dev.beans.ProductOrderDto;
import com.dev.beans.SalesOrderDto;

public class TestUtil {
	public static CustomerDto createCustomerDto1(){
		return CustomerDto.create()
				.setCode("10")
				.setCurrentCredit(100.0)
				.setName("NoName")
				.setPhone1("9999-bbbb")
				.setPhone2("7777-bbbb")
				.setAddress("St. None")
				.setCreditLimit(800.0)
				.build();
	}

	public static Customer createCustomer1(){
		return Customer.fromDto(createCustomerDto1());
	}

	public static CustomerDto createCustomerDto2() {
		return CustomerDto.create()
				.setCode("20")
				.setCurrentCredit(5.2)
				.setName("NoName 2")
				.setPhone1("5555-bbbb")
				.setPhone2("1234-bbbb")
				.setAddress("St. Old")
				.setCreditLimit(19.0)
				.build();
	}

	public static Customer createCustomer2(){
		return Customer.fromDto(createCustomerDto2());
	}

	public static CustomerDto createCustomerDto3() {
		return CustomerDto.create()
				.setCode("30")
				.setCurrentCredit(8.9)
				.setName("NoName 3")
				.setPhone1("0989-bbbb")
				.setPhone2("55555-bbbb")
				.setAddress("St. Elder")
				.setCreditLimit(96.8)
				.build();
	}

	public static Customer createCustomer3(){
		return Customer.fromDto(createCustomerDto3());
	}

	public static List<Customer> createCustomerList(){
		return Arrays.asList(createCustomer1(), createCustomer2());
	}

	private static void checkNotNullAndEquals(Object object1, Object object2){
		assertNotNull(object1);
		assertNotNull(object2);
		assertEquals(object1, object2);		
	}

	public static void compare(Customer customer1, Customer customer2){
		checkNotNullAndEquals(customer1.getCode(), customer2.getCode());
		checkNotNullAndEquals(customer1.getName(), customer2.getName());
		checkNotNullAndEquals(customer1.getPhone1(), customer2.getPhone1());
		checkNotNullAndEquals(customer1.getPhone2(), customer2.getPhone2());
		checkNotNullAndEquals(customer1.getAddress(), customer2.getAddress());
		checkNotNullAndEquals(customer1.getCreditLimit(), customer2.getCreditLimit());
		checkNotNullAndEquals(customer1.getCurrentCredit(), customer2.getCurrentCredit());
	}

	public static List<CustomerDto> createCustomerDtoList() {
		return Arrays.asList(createCustomerDto1(), createCustomerDto2());
	}

	public static void compare(CustomerDto customer1, CustomerDto customer2) {
		checkNotNullAndEquals(customer1.getCode(), customer2.getCode());
		checkNotNullAndEquals(customer1.getName(), customer2.getName());
		checkNotNullAndEquals(customer1.getPhone1(), customer2.getPhone1());
		checkNotNullAndEquals(customer1.getPhone2(), customer2.getPhone2());
		checkNotNullAndEquals(customer1.getAddress(), customer2.getAddress());
		checkNotNullAndEquals(customer1.getCreditLimit(),customer2.getCreditLimit());
		checkNotNullAndEquals(customer1.getCurrentCredit(), customer2.getCurrentCredit());
	}

	public static ProductDto createProductDto1(){
		return ProductDto.create()
				.setCode("10")
				.setDescription("Test1")
				.setPrice(5.0)
				.setQuantity(100)
				.build();
	}
	
	public static ProductOrderDto createProductOrderDto1(){
		return ProductOrderDto.create()
				.setCode(createProductDto1().getCode())
				.setPrice(createProductDto1().getPrice())
				.setQuantity(60)
				.build();				
	}


	public static Product createProduct1(){
		return Product.fromDto(createProductDto1());
	}

	public static ProductDto createProductDto2(){
		return ProductDto.create()
				.setCode("20")
				.setDescription("Test2")
				.setPrice(100.0)
				.setQuantity(20)
				.build();
	}

	public static ProductOrderDto createProductOrderDto2(){
		ProductDto product = createProductDto2();
		return ProductOrderDto.create()
				.setCode(product.getCode())
				.setPrice(product.getPrice())
				.setQuantity(5)
				.build();				
	}

	public static Product createProduct2(){
		return Product.fromDto(createProductDto2());
	}


	public static List<Product> createProductList() {
		return Arrays.asList(createProduct1(), createProduct2());
	}
	public static List<ProductDto> createProductDtoList() {
		return Arrays.asList(createProductDto1(), createProductDto2());
	}

	public static void compare(Product product1, Product product2) {
		checkNotNullAndEquals(product1.getCode(), product2.getCode());
		checkNotNullAndEquals(product1.getDescription(), product2.getDescription());
		checkNotNullAndEquals(product1.getPrice(), product2.getPrice());
		checkNotNullAndEquals(product1.getQuantity(), product2.getQuantity());		
	}

	public static void compare(ProductDto product1, ProductDto product2) {
		checkNotNullAndEquals(product1.getCode(), product2.getCode());
		checkNotNullAndEquals(product1.getDescription(), product2.getDescription());
		checkNotNullAndEquals(product1.getPrice(), product2.getPrice());
		checkNotNullAndEquals(product1.getQuantity(), product2.getQuantity());		
	}

	public static ProductDto createProductDto3(){
		return ProductDto.create()
				.setCode("30")
				.setDescription("Test3")
				.setPrice(1111.232)
				.setQuantity(10)
				.build();
	}

	public static Product createProduct3(){
		return Product.fromDto(createProductDto3());
	}

	/**
	 * Creates a sales order for client1, price 300.
	 * @return
	 */
	public static SalesOrderDto createSalesOrderDto1(){
		return SalesOrderDto.create()
				.setCustomerCode(createCustomerDto1().getCode())
				.setCustomerName(createCustomerDto1().getName())
				.setOrderNum("200")
				.setProductOrders(createProductOrdersDto())
				.build();
	}
	
	public static SalesOrderDto createSalesOrderDtoOtherCode1() {
		return SalesOrderDto.create()
				.setCustomerCode(createCustomerDto1().getCode())
				.setCustomerName(createCustomerDto1().getName())
				.setOrderNum("210")
				.setProductOrders(createProductOrdersDto())
				.build();
	}

		
	public static ProductOrder createProductOrder1(){
		return ProductOrder.fromDto(createProductOrderDto1(), createProduct1());			
	}

	
	public static List<ProductOrderDto> createProductOrdersDto() {		
		return Arrays.asList(createProductOrderDto1());
	}

	public static List<ProductOrder> createProductOrders() {		
		return Arrays.asList(createProductOrder1());
	}

	public static SalesOrder createSalesOrder1() {
		return SalesOrder.fromDto(createSalesOrderDto1(), createCustomer1(), createProductOrders());
	}

	public static void compare(SalesOrder order1, SalesOrder order2) {
		checkNotNullAndEquals(order1.getOrderNum(), order2.getOrderNum());
		checkNotNullAndEquals(order1.getCustomer(), order2.getCustomer());
		if (order1.getProductOrders()!=null && order2.getProductOrders()!=null){
			assertEquals(order1.getProductOrders().size(), order2.getProductOrders().size());
			for (int i=0;i<order1.getProductOrders().size();i++){
				compare(order1.getProductOrders().get(i), order2.getProductOrders().get(i));
			}

		} else {
			if (order1.getProductOrders()!=null || order2.getProductOrders()!=null) {
				//One is null, the other is not.
				assertTrue(false);
			}
		}
			
	}

	public static void compare(ProductOrder productOrder, ProductOrder productOrder2) {
		checkNotNullAndEquals(productOrder.getProduct(), productOrder2.getProduct());
		checkNotNullAndEquals(productOrder.getPrice(), productOrder2.getPrice());
		checkNotNullAndEquals(productOrder.getQuantity(), productOrder2.getQuantity());				
	}

	public static SalesOrderDto createSalesOrderDto2(){
		CustomerDto customerDto = createCustomerDto2();
		return SalesOrderDto.create()
				.setCustomerCode(customerDto.getCode())
				.setCustomerName(customerDto.getName())				
				.setOrderNum("90")
				.setProductOrders(createProductOrdersDto2())
				.build();
	}

	public static List<ProductOrderDto> createProductOrdersDto2() {		
		return Arrays.asList(createProductOrderDto2());
	}
	
	public static ProductOrder createProductOrder2(){
		Product product = createProduct2();
		return ProductOrder.fromDto(createProductOrderDto2(), product);			
	}

	public static List<ProductOrder> createProductOrders2() {		
		return Arrays.asList(createProductOrder2());
	}

	public static SalesOrder createSalesOrder2() {
		return SalesOrder.fromDto(createSalesOrderDto2(), createCustomer2(),  createProductOrders2());
	}

	public static List<SalesOrderDto> createSalesOrderDtoList() {
		return Arrays.asList(createSalesOrderDto1(), createSalesOrderDto2());
	}

	public static Collection<SalesOrder> createSalesOrderList() {
		return Arrays.asList(createSalesOrder1(), createSalesOrder2());
	}

	public static void compare(SalesOrderDto order1, SalesOrderDto order2) {
		checkNotNullAndEquals(order1.getOrderNum(), order2.getOrderNum());
		checkNotNullAndEquals(order1.getCustomerCode(), order2.getCustomerCode());
		checkNotNullAndEquals(order1.getCustomerName(), order2.getCustomerName());
		if (order1.getProductOrders()!=null && order2.getProductOrders()!=null){
			assertEquals(order1.getProductOrders().size(), order2.getProductOrders().size());
			for (int i=0;i<order1.getProductOrders().size();i++){
				compare(order1.getProductOrders().get(i), order2.getProductOrders().get(i));
			}

		} else {
			if (order1.getProductOrders()!=null || order2.getProductOrders()!=null) {
				//One is null, the other is not.
				assertTrue(false);
			}
		}
	}

	public static void compare(ProductOrderDto productOrder, ProductOrderDto productOrder2) {
		checkNotNullAndEquals(productOrder.getProductCode(), productOrder2.getProductCode());
		checkNotNullAndEquals(productOrder.getProductPrice(), productOrder2.getProductPrice());
		checkNotNullAndEquals(productOrder.getProductQuantity(), productOrder2.getProductQuantity());				
	}

	public static SalesOrderDto createSalesOrderDto3(){
		CustomerDto customer = createCustomerDto3(); 
		return SalesOrderDto.create()
				.setCustomerCode(customer.getCode())
				.setCustomerName(customer.getName())								
				.setOrderNum("130")
				.setProductOrders(createProductOrdersDto3())
				.build();
	}

	/**
	 * Creates an order of 8 * Product3 (price > 8888)
	 * @return
	 */
	public static List<ProductOrderDto> createProductOrdersDto3() {	
		return Arrays.asList(createProductOrderDto3());
	}

	public static ProductOrderDto createProductOrderDto3(){
		ProductDto product = createProductDto3();
		return ProductOrderDto.create()
				.setCode(product.getCode())
				.setPrice(product.getPrice())
				.setQuantity(8)
				.build();				
	}
	
	public static ProductOrder createProductOrder3(){
		Product product = createProduct3();
		return ProductOrder.fromDto(createProductOrderDto3(), product);			
	}
	
	public static List<ProductOrder> createProductOrders3() {		
		return Arrays.asList(createProductOrder3());
	}

	public static SalesOrder createSalesOrder3() {		
		return SalesOrder.fromDto(createSalesOrderDto3(), createCustomer3(), createProductOrders3());
	}

	public static Customer createCustomer1Modified() {
		Customer original = createCustomer1();
		return Customer.create()
				.setCode(original.getCode())
				.setAddress("new Address")
				.setName("New Name")
				.setPhone1("No")
				.setPhone2("Different")
				.setCreditLimit(99.9)
				.setCurrentCredit(1000.0)
				.build();
	}

	public static Product createProduct1Modified() {
		Product original = createProduct1();
		return Product.create()
				.setCode(original.getCode())
				.setDescription("New")
				.setPrice(1010.0)
				.setQuantity(10000)
				.build();
	}

	public static SalesOrder createSalesOrder1Modified() {
		SalesOrder original = createSalesOrder1();
		return SalesOrder.create()
				.setOrderNum(original.getOrderNum())
				.setCustomer(createCustomer2())
				.setProductOrders(createProductOrders3())
				.build();
	}

	public static SalesOrderDto createSalesOrderMoreThanStock() {
		CustomerDto customerDto = createCustomerDto1();
		List<ProductOrderDto> products = Arrays.asList(createProductOrderDto1(), createProductOrderDto1());
		return SalesOrderDto.create()
				.setOrderNum("999")
				.setCustomerCode(customerDto.getCode())
				.setProductOrders(products)
				.build();
	}

	public static SalesOrderDto createSalesOrderMoreThanCredit() {
		CustomerDto customerDto = createCustomerDto1(); // Credit limit: +700
		List<ProductOrderDto> products = Arrays.asList(createProductOrderDto1(), createProductOrderDto2()); //+800, over credit.
		return SalesOrderDto.create()
				.setOrderNum("555")
				.setCustomerCode(customerDto.getCode())
				.setProductOrders(products)
				.build();
	}

	public static SalesOrderDto createSalesOrderDtoOtherCode2() {
		CustomerDto customerDto = createCustomerDto1(); 
		List<ProductOrderDto> products = Arrays.asList(createProductOrderDto2()); //+800, over credit.
		return SalesOrderDto.create()
				.setOrderNum("666")
				.setCustomerCode(customerDto.getCode())
				.setProductOrders(products)
				.build();
	}
		
}
