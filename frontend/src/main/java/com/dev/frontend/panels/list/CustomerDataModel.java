package com.dev.frontend.panels.list;

import java.util.List;

import com.dev.beans.CustomerDto;
import com.dev.frontend.services.Services;

public class CustomerDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747613655L;

	public CustomerDataModel()
	{
		super(new String[] { "Code", "Name", "Phone", "Current Credit" }, 0);
	}

	@Override
	public int getObjectType()
	{
		return Services.TYPE_CUSTOMER;
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
			CustomerDto customer = (CustomerDto) o;
			String[] entry = new String[] {customer.getCode(), customer.getName(), customer.getPhone1(), String.valueOf(customer.getCurrentCredit())};
			entries[i] = entry;
			i++;
		}
		return entries;
	}
}
