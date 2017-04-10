package com.service;

import com.entities.Cart;
import com.entities.CartItem;

public interface CartItemService {

	void addCartItem(CartItem cartItem);

	void removeCartItem(CartItem cartItem);

	void removeAllCartItems(Cart cart);

	CartItem getCartItemByProductId(int productId);
}