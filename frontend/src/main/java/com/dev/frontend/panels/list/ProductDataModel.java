package com.dev.frontend.panels.list;

import java.util.List;

import com.dev.beans.ProductDto;
import com.dev.frontend.panels.edit.TextUtil;
import com.dev.frontend.services.Services;


public class ProductDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747614655L;

	public ProductDataModel() 
	{
		super(new String[]{"Code","Description","Price","Quantity"}, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_PRODUCT;
	}

	@Override
	public String[][] convertRecordsListToTableModel(List<Object> list) 
	{
		if (list==null){
			return null;
		}
		String[][] entries = new String[list.size()][4];
		int i=0;
		for (Object o: list){
			ProductDto product = (ProductDto) o;
			String[] entry = new String[] {product.getCode(), product.getDescription(), TextUtil.convert(product.getPrice()), TextUtil.convert(product.getQuantity())};
			entries[i] = entry;
			i++;
		}
		return entries;
	}
}
