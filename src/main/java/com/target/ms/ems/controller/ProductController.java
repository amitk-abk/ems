package com.target.ms.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.target.ms.ems.response.ProductResponse;
import com.target.ms.ems.response.RemoveAttribute;
import com.target.ms.ems.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/product")
	@ResponseStatus(HttpStatus.CREATED)
	public String createProduct(@RequestBody ProductResponse productReponse) {
		String response = productService.createProduct(productReponse);
		logger.info("Created product with code {}", productReponse.getProductCode());
		return response;
	}

	@GetMapping("/product/{productCode}")
	@ResponseStatus(HttpStatus.FOUND)
	public ProductResponse getProductForProductCode(@PathVariable String productCode) {
		ProductResponse product = productService.getProductFor(productCode);
		logger.info("Retrieved the product details for produt code {}", productCode);
		return product;
	}

	@PutMapping("/product/{productCode}/{attributeName}")
	@ResponseStatus(HttpStatus.OK)
	public String addAttributeToProduct(@PathVariable String productCode, @PathVariable String attributeName) {
		String response = productService.addAttributeToProduct(productCode, attributeName);
		logger.info("Added attribute {} to product with code {}", attributeName, productCode);
		return response;
	}
	
	@PutMapping("/product")
	@ResponseStatus(HttpStatus.OK)
	public String removeAttribute(@RequestBody RemoveAttribute removeAttribute) {
		String response = productService.removeAttribute(removeAttribute);
		logger.info("Removed the attribute(s) from product {}", removeAttribute.getProductCode());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String operationException(Exception e) {
		logger.error("Exception occured in service request, message is {}", e.getMessage(), e);
		return "Something went wrong in processing the request, kindly check the payload or request";
	}
}
