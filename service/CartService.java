package com.service;

import com.entities.Cart;

public interface CartService {

	Cart getCartById(int cartId);

	void update(Cart cart);
}
