package com.dev.backend.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.backend.bean.SalesOrder;
import com.dev.backend.bean.TestUtil;
import com.dev.backend.dao.SalesOrderDao;

@Service
class SalesOrderDaoStub implements SalesOrderDao{
	
	private Map<String, SalesOrder> salesOrders;
	
	public SalesOrderDaoStub(){
		reset();
	}

	@Override
	public long count() {
		return salesOrders.size();
	}

	@Override
	public void delete(String arg0) {
		salesOrders.remove(arg0);		
	}

	@Override
	public void delete(SalesOrder arg0) {
		salesOrders.remove(arg0.getOrderNum());		
	}

	@Override
	public void delete(Iterable<? extends SalesOrder> arg0) {
		for (SalesOrder salesOrder: arg0){
			delete(salesOrder);
		}
		
	}

	@Override
	public void deleteAll() {
		salesOrders.clear();
		
	}

	@Override
	public boolean exists(String arg0) {
		return salesOrders.containsKey(arg0);
	}

	@Override
	public Iterable<SalesOrder> findAll() {
		return salesOrders.values();
	}

	@Override
	public Iterable<SalesOrder> findAll(Iterable<String> arg0) {
		Set<SalesOrder> salesOrderSet = new HashSet<>();
		for (String key: arg0){
			salesOrderSet.add(salesOrders.get(key));
		}
		return salesOrderSet;
	}

	@Override
	public SalesOrder findOne(String arg0) {
		return salesOrders.get(arg0);
	}

	@Override
	public <S extends SalesOrder> S save(S arg0) {
		System.out.println("Simulating saving of:" + arg0);
		salesOrders.put(arg0.getOrderNum(), arg0);
		return arg0;
	}

	@Override
	public <S extends SalesOrder> Iterable<S> save(Iterable<S> arg0) {
		for (SalesOrder p: arg0){
			save(p);
		}
		return arg0;
	}

	public void reset() {
		salesOrders = TestUtil.createSalesOrderList()
				.stream()
				.collect(Collectors.toMap(SalesOrder::getOrderNum,
                Function.identity()));
	}

}
