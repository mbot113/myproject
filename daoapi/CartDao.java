package com.daoapi;

import java.io.IOException;

import com.entities.Cart;

public interface CartDao {

	Cart getCartById(int cartId);
	
	Cart validate(int cartId) throws IOException;

	void update(Cart cart);
}
