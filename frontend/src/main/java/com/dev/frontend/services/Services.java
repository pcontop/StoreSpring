package com.dev.frontend.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dev.beans.CustomerDto;
import com.dev.beans.ProductDto;
import com.dev.frontend.panels.ComboBoxItem;

public class Services 
{
	public static final int TYPE_PRODUCT = 1;
	public static final int TYPE_CUSTOMER = 2;
	public static final int TYPE_SALESORDER = 3;

	private static Map<Integer, RestServices<?>> restServiceMap;

	static {
		initializeServiceMap();
	}


	private static void initializeServiceMap() {
		restServiceMap = new HashMap<>();
		restServiceMap.put(TYPE_PRODUCT, new ProductServices());
		restServiceMap.put(TYPE_CUSTOMER, new CustomerServices());
		restServiceMap.put(TYPE_SALESORDER, new SalesOrderServices());
	}


	private static RestServices<?> getService(int objectType){
		return restServiceMap.get(objectType);
	}

	public static Object save(Object object,int objectType)
	{	
		return getService(objectType).save(object);
	}
	public static Object readRecordByCode(String code,int objectType)
	{
		return getService(objectType).get(code);
	}
	public static boolean deleteRecordByCode(String code,int objectType)
	{
		return getService(objectType).delete(code);
	}

	public static List<Object> listCurrentRecords(int objectType)
	{
		List<Object>  objects = getService(objectType).getAll();

		return objects;
	}
	public static List<ComboBoxItem> listCurrentRecordRefernces(int objectType) 
	{	
		List<Object> objects = getService(objectType).getAll();
		List<ComboBoxItem> comboBoxItems;

		switch (objectType){
		case TYPE_CUSTOMER:
			comboBoxItems = objects.stream()
			.map(o -> { 
				CustomerDto c = (CustomerDto) o;
				return new ComboBoxItem(c.getCode(), c.getName());
			}).collect(Collectors.toList());
			break;
		case TYPE_PRODUCT:
			comboBoxItems = objects.stream()
			.map(o -> { 
				ProductDto c = (ProductDto) o;
				return new ComboBoxItem(c.getCode(), c.getDescription());
			}).collect(Collectors.toList());
			break;
		default: comboBoxItems = new ArrayList<>();
		}
		return comboBoxItems;
	}
	public static double getProductPrice(String productCode) {
		ProductDto productDto = (ProductDto) getService(TYPE_PRODUCT).get(productCode);
		return productDto.getPrice();
	}


}
