package com.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entities.Product;
import com.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/productList/all")
	public String getProducts(Model model) {
		List<Product> products = productService.getProductList();
		model.addAttribute("products", products);

		return "productList";
	}

	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) throws IOException {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);

		return "viewProduct";
	}

	@RequestMapping("/productList")
	/*
	 * public String getProductByCategory(@RequestParam("searchCondition")
	 * String searchCondition, Model model) {
	 */
	public String getProductByCategory(Model model) {
		List<Product> products = productService.getProductList();
		model.addAttribute("products", products);
		/*
		 * pass to searchCondition an empty string. I can comment the next 2
		 * lines. The porject will run fine.
		 */
		String searchCondition = "";
		model.addAttribute("searchCondition", searchCondition);

		return "productList";
	}

}
