package com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 5634982649083216302L;

	@Id
	@GeneratedValue
	private int customerOrderId;

	/* One CustomerOrder to One cart */
	@OneToOne
	@JoinColumn(name = "cartId")
	private Cart cart;

	/* One CustomerOrder to One customer */
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	/* One CustomerOrder to One billingAddress */
	@OneToOne
	@JoinColumn(name = "billingAddressId")
	private BillingAddress billingAddress;

	/* One CustomerOrder to One shippingAddress */
	@OneToOne
	@JoinColumn(name = "shippingAddressId")
	private ShippingAddress shippingAddress;

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
