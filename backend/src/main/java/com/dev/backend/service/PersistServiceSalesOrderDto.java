package com.dev.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.bean.Customer;
import com.dev.backend.bean.Product;
import com.dev.backend.bean.ProductOrder;
import com.dev.backend.bean.SalesOrder;
import com.dev.backend.bean.SalesOrderFactory;
import com.dev.backend.dao.CustomerDao;
import com.dev.backend.dao.ProductDao;
import com.dev.backend.dao.SalesOrderDao;
import com.dev.beans.SalesOrderDto;

/**
 * Implements the CRUD and business rules for the persistence and the retrieval of SalesOrders.
 * This Services will persist and retrieve all data using the injected instanced of 
 * SalesOrderDao, ProductDao and CustomerDao.
 *   
 * @author pcont_000
 *
 */
@Service
public class PersistServiceSalesOrderDto implements PersistService<SalesOrderDto>{
	@Autowired
	SalesOrderDao salesOrderDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	SalesOrderFactory salesOrderFactory;

	/**
	 * Saves the SalesOrder. It checks if the order exists first, then checks the 
	 * stock quantities and customer's credit. If all is ok, persists, adds credit 
	 * and reduces stock. If not, rejects the operation.
	 * 
	 * @return If the operation was completed successfully or not.
	 */
	@Override	
	public boolean save(SalesOrderDto salesOrderDto) {
		SalesOrder salesOrder = salesOrderFactory.fromDto(salesOrderDto);
		if (salesOrder==null){
			//Something was missing, be it a Customer or a Product.
			return false;
		}
		treatIfExists(salesOrder);
		if (!checkStockAndSubtract(salesOrder)){
			throw new IllegalArgumentException("Order Rejected! This order goes over stock!");
		}
		if (!checkPriceAndAddCredit(salesOrder)){
			throw new IllegalArgumentException("Order Rejected! This order goes over the user's credit!");
		}
		System.out.println("Order Persisted.");
		salesOrderDao.save(salesOrder);
		return true;
	}

	/**
	 * If an order is being updated, the former order must be removed first. 
	 * The stock will be replaced, and the credit refunded to the Customer. 
	 * @param salesOrder
	 */
	private SalesOrder treatIfExists(SalesOrder salesOrder) {
		SalesOrder existingOrder = salesOrderDao.findOne(salesOrder.getOrderNum());
		if (existingOrder==null){
			return null;
		}
		reduceCreditFromCustomer(existingOrder);
		returnToStock(existingOrder);
		removeSalesOrder(existingOrder);
		return existingOrder;
		
	}

	/**
	 * Remove the older salesOrder from the database.
	 * @param existingOrder
	 */
	private void removeSalesOrder(SalesOrder existingOrder) {
		salesOrderDao.delete(existingOrder.getOrderNum());
		
	}

	/**
	 * Returns the stock from the former order.
	 * @param existingOrder
	 */
	private void returnToStock(SalesOrder existingOrder) {
		Map<Product, Integer> quantities = getQuantityMap(existingOrder);
		for (Product product: quantities.keySet()){
			Integer quantity = quantities.get(product);
			subtractStockAndSave(product, -quantity);
		}
		
	}

	/**
	 * Reduces spent credit to the user from the former order.
	 * @param existingOrder
	 */
	private void reduceCreditFromCustomer(SalesOrder existingOrder) {
		double price = getPrice(existingOrder);
		Customer customer = existingOrder.getCustomer();
		addCustCreditAndSave(customer, -price);				
	}

	/**
	 * Checks price from the received order to the user's. If ok, adds credit to the customer. 
	 * @param salesOrder
	 * @return
	 */
	private boolean checkPriceAndAddCredit(SalesOrder salesOrder) {
		Customer customer = salesOrder.getCustomer();
		double priceOrder = getPrice(salesOrder);
		if (priceOrder + customer.getCurrentCredit() > customer.getCreditLimit()){
			return false;
		}
		addCustCreditAndSave(customer, priceOrder);
		return true;
	}

	/**
	 * Adds used credit to the customer and persists.
	 * @param customer
	 * @param priceOrder
	 */
	private void addCustCreditAndSave(Customer customer, double priceOrder) {
		customer.addCredit(priceOrder);
		customerDao.save(customer);
	}

	/**
	 * Calculates the price from an order.
	 * @param salesOrder
	 * @return
	 */
	private double getPrice(SalesOrder salesOrder) {
		double priceOrder = salesOrder.getProductOrders().stream()
				.mapToDouble(po -> po.getQuantity()  * po.getProduct().getPrice())
				.sum();
		return priceOrder;
	}

	/**
	 * Checks stock. If enough to satisfy the order, subtracts the values. 
	 * @param salesOrder
	 * @return
	 */
	private boolean checkStockAndSubtract(SalesOrder salesOrder) {
		Map<Product, Integer> quantities = getQuantityMap(salesOrder);
		for (Product product: quantities.keySet()){
			Integer quantity = quantities.get(product);
			if (product.getQuantity() < quantity){
				return false;				
			} else {
				//Reduces stock.
				subtractStockAndSave(product, quantity);
			}
		}
				
		return true;
	}

	/**
	 * Subtracts quantity from stock and persists.
	 * @param product
	 * @param quantity
	 */
	private void subtractStockAndSave(Product product, Integer quantity) {
		product.subtractQuantity(quantity);
		productDao.save(product);
	}

	/**
	 * Gets a map with the quantity of each product from the SalesOrder.
	 * @param salesOrder
	 * @return
	 */
	private Map<Product, Integer> getQuantityMap(SalesOrder salesOrder) {
		List<ProductOrder> productOrders = salesOrder.getProductOrders();
		Map<Product, Integer> quantities = productOrders.stream()
				.collect(
			            Collectors.groupingBy(
			                ProductOrder::getProduct,                      
			                Collectors.reducing(
			                    0,
			                    ProductOrder::getQuantity,
			                    Integer::sum)));
		return quantities;
	}

	/**
	 * Returns a SalesOrderDto from the order requested, if it exists.
	 * @param The order number.
	 * @return The salesOrderDto. 
	 */
	@Override
	public SalesOrderDto get(String orderNum) {
		SalesOrder salesOrder = getPersistedSalesOrder(orderNum);
		if (salesOrder==null){
			return null;
		}
		return salesOrder.toDto();
	}

	/**
	 * Returns a persisted order from the Dao. 
	 * @param orderNum The order number.
	 * @return The existing sales order.
	 */
	private SalesOrder getPersistedSalesOrder(String orderNum) {
		SalesOrder salesOrder = salesOrderDao.findOne(orderNum);
		return salesOrder;
	}

	
	/**
	 * Deletes a Sales Order from the register. 
	 * Returns credit to the user and products to stock. 
	 */
	@Override
	public boolean delete(String orderNum) {
		SalesOrder salesOrder = getPersistedSalesOrder(orderNum);
		if (salesOrder!=null){
			treatIfExists(salesOrder);
		}
		return true;
	}

	@Override
	/**
	 * Returns all orders already made.
	 */
	public List<SalesOrderDto> getAll() {
		Iterable<SalesOrder> salesOrders = salesOrderDao.findAll();		
		List<SalesOrderDto> salesOrderDtos = new ArrayList<>();
		for (SalesOrder saleOrder: salesOrders){
			salesOrderDtos.add(saleOrder.toDto());
		}
		return salesOrderDtos;
	}

	@Override
	/**
	 * Method used only for test. This method does *not* revert the credit and 
	 * returns the ordered products to the stock!
	 */
	public void deleteAll() {		
		salesOrderDao.deleteAll();
	}
	

}
