package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.entities.Cart;
import com.entities.CartItem;
import com.entities.Customer;
import com.entities.Product;
import com.service.CartItemService;
import com.service.CartService;
import com.service.CustomerService;
import com.service.ProductService;

@Controller
@RequestMapping("/rest/cart")
public class CartResources {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/{cartId}")
	public @ResponseBody Cart getCartById(@PathVariable(value = "cartId") int cartId) {
		return cartService.getCartById(cartId);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable(value = "productId") int productId, @AuthenticationPrincipal User activeUser) {

		System.out.println("CartResources addItem!!!!!!!!!!!!!!!!!!!");
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
		System.out.println("1. FAIL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Cart cart = customer.getCart();
		System.out.println("2. FAIL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Product product = productService.getProductById(productId);
		System.out.println("3. FAIL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		List<CartItem> cartItems = cart.getCartItems();

		/*
		 * if the product is already exists,retrieve the cart and then update
		 * quantity, total price
		 */
		for (int i = 0; i < cartItems.size(); i++) {		
			if (product.getProductId() == cartItems.get(i).getProduct().getProductId()) {
				System.out.println("INSIDE IF !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getProductPrice() * cartItem.getQuantity());
				System.out.println("cartItem = " + cartItem.toString());
				cartItemService.addCartItem(cartItem);
				System.out.println("TEST WHAT PRINTS" + cartItemService.toString());				
				return;
			}
		}
		/* if the product does not exist create a new one */
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getProductPrice() * cartItem.getQuantity());
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
	}
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem (@PathVariable(value = "productId") int productId) {
        CartItem cartItem = cartItemService.getCartItemByProductId(productId);
        cartItemService.removeCartItem(cartItem);

    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable(value = "cartId") int cartId) {
        Cart cart = cartService.getCartById(cartId);
        cartItemService.removeAllCartItems(cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload.")
    public void handleClientErrors (Exception e) {}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error.")
    public void handleServerErrors (Exception e) {}
}