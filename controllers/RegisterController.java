package com.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.BillingAddress;
import com.entities.Customer;
import com.entities.ShippingAddress;
import com.service.CustomerService;

@Controller
public class RegisterController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/register")
	public String registerCustomer(Model model) {

		Customer customer = new Customer();
		BillingAddress billingAddress = new BillingAddress();
		ShippingAddress shippingAddress = new ShippingAddress();
		customer.setBillingAddress(billingAddress);
		customer.setShippingAddress(shippingAddress);

		model.addAttribute("customer", customer);

		return "registerCustomer";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCustomerPost(@Valid @ModelAttribute("customer") Customer customer, BindingResult result,
			Model model) {

		boolean initTest = true, availability = true;
		/* if any field is null, the result has errors */
		if (result.hasErrors()) {
			initTest = false;
		}
		/*retrieve all the customers to check availability of username, email*/
		List<Customer> customerList = customerService.getAllCustomers();
		for (int i = 0; i < customerList.size(); i++) {

			if (customer.getCustomerEmail().equals(customerList.get(i).getCustomerEmail())) {
				model.addAttribute("emailMsg", "Email already exists");
				availability = false;
				if (customer.getUsername().equals(customerList.get(i).getUsername())) {					
					model.addAttribute("usernameMsg", "Username already exists");
				}
			}
			if (customer.getUsername().equals(customerList.get(i).getUsername())) {
				model.addAttribute("usernameMsg", "Username already exists");
				availability = false;
			}
			if (availability == false || initTest== false) {
				return "registerCustomer";
			}
		}
		customer.setEnabled(true);
		customerService.addCustomer(customer);
		return "registerCustomerSuccess";
	}

}