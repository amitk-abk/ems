package com.target.ms.ems.service;

import org.springframework.transaction.annotation.Transactional;
import com.target.ms.ems.response.ProductResponse;
import com.target.ms.ems.response.RemoveAttribute;


public interface ProductService {

	@Transactional
	String createProduct(ProductResponse productReponse);

	@Transactional(readOnly = true)
	ProductResponse getProductFor(String productCode);

	@Transactional
	String addAttributeToProduct(String productCode, String attributeName);

	@Transactional
	String removeAttribute(RemoveAttribute removeAttribute);
}
