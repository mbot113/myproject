package com.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daoapi.CustomerDao;
import com.entities.Authorities;
import com.entities.Cart;
import com.entities.Customer;
import com.entities.Users;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();

		customer.getBillingAddress().setCustomer(customer);
		customer.getShippingAddress().setCustomer(customer);

		session.saveOrUpdate(customer);
		session.saveOrUpdate(customer.getBillingAddress());
		session.saveOrUpdate(customer.getShippingAddress());

		Users newUser = new Users();
		newUser.setUsername(customer.getUsername());
		newUser.setPassword(passwordEncoder.encode(customer.getPassword()));
		// newUser.setPassword(customer.getPassword());
		newUser.setEnabled(true);
		newUser.setCustomerId(customer.getCustomerId());

		Authorities newAuthority = new Authorities();
		newAuthority.setUsername(customer.getUsername());
		if (!(newAuthority.getUsername().equals("admin"))) {
			System.out.println("User!!!!!");
			newAuthority.setAuthority("ROLE_USER");
		} else {
			System.out.println("Admin!!!!!");
			newAuthority.setAuthority("ROLE_ADMIN");
		}
		session.saveOrUpdate(newUser);
		session.saveOrUpdate(newAuthority);

		Cart newCart = new Cart();
		newCart.setCustomer(customer);
		customer.setCart(newCart);
		session.saveOrUpdate(customer);
		session.saveOrUpdate(newCart);

		session.flush();
	}

	public Customer getCustomerById(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		return (Customer) session.get(Customer.class, customerId);
	}

	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Customer");
		List<Customer> customerList = query.list();

		return customerList;
	}

	public Customer getCustomerByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Customer where username = ?");
		query.setString(0, username);

		return (Customer) query.uniqueResult();
	}
}
