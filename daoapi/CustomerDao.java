package com.daoapi;

import java.util.List;

import com.entities.Customer;

public interface CustomerDao {
	void addCustomer(Customer customer);

	Customer getCustomerById(int customerId);

	List<Customer> getAllCustomers();

	Customer getCustomerByUsername(String username);
}
