package com.dev.frontend.panels.list;

import java.util.List;

import com.dev.beans.SalesOrderDto;
import com.dev.frontend.panels.edit.TextUtil;
import com.dev.frontend.services.Services;


public class SalesOrderDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747614655L;

	public SalesOrderDataModel() 
	{
		super(new String[]{"Order Number","Customer","Total Price"}, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_SALESORDER;
	}

	@Override
	public String[][] convertRecordsListToTableModel(List<Object> list) 
	{
		String [][] entries = new String[list.size()][3];
		int i=0;
		for (Object o: list){
			SalesOrderDto salesOrder = (SalesOrderDto) o;
			String[] entry = new String[] {salesOrder.getOrderNum(), 
					"(" + salesOrder.getCustomerCode() + ")" + salesOrder.getCustomerName(), 
					TextUtil.convert(salesOrder.getTotalPrice())};
			entries[i] = entry;
			i++;
		}				
		return entries;
	}
}
