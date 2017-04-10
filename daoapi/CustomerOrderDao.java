package com.daoapi;

import com.entities.CustomerOrder;

public interface CustomerOrderDao {

	void addCustomerOrder(CustomerOrder customerOrder);

	//double getCustomerOrderGrandTotal(int cartId);
}
