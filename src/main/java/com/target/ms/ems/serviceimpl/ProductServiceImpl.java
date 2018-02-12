package com.target.ms.ems.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.ms.ems.model.Attribute;
import com.target.ms.ems.model.AttributeValue;
import com.target.ms.ems.model.Catalog;
import com.target.ms.ems.model.Product;
import com.target.ms.ems.model.Review;
import com.target.ms.ems.repository.AttributeRepository;
import com.target.ms.ems.repository.AttributeValueRepository;
import com.target.ms.ems.repository.CatalogRepository;
import com.target.ms.ems.repository.ProductRepository;
import com.target.ms.ems.response.ProductAttributes;
import com.target.ms.ems.response.ProductResponse;
import com.target.ms.ems.response.RemoveAttribute;
import com.target.ms.ems.response.ReviewResponse;
import com.target.ms.ems.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private AttributeValueRepository attributeValueRepository;

	@Override
	public String createProduct(ProductResponse productResponse) {
		Product product = productRepository.findByProductCode(productResponse.getProductCode());
		if (product != null)
			return "The product:" + productResponse.getProductName() + " already exists";

		Catalog catalog = catalogRepository.findByCatalogCode(productResponse.getCatalogCode());
		if (catalog == null)
			return "The catalog:" + productResponse.getCatalogCode() + " does not exists";

		product = new Product();
		List<AttributeValue> attributeValues = getAttributeValuesFor(productResponse);

		product.setCatalog(catalog);
		product.setDisplayName(productResponse.getDisplayName());
		product.setPrice(productResponse.getPrice());
		product.setProductCode(productResponse.getProductCode());
		product.setProductDescription(productResponse.getProductDescription());
		product.setProductName(productResponse.getProductName());
		product.setAttributeValues(attributeValues);
		productRepository.save(product);
		return "Created product:" + product.getProductName();
	}

	private List<AttributeValue> getAttributeValuesFor(ProductResponse productResponse) {
		List<AttributeValue> attributeValues = new ArrayList<>();
		for (ProductAttributes productAttribute : productResponse.getAttributes()) {
			String attributeName = productAttribute.getAttribute();
			Attribute attribute = attributeRepository.findByName(attributeName);
			AttributeValue avalue = attributeValueRepository.findByAttributeAndValue(attribute,
					productAttribute.getAttributeValue());
			attributeValues.add(avalue);
		}		
		return attributeValues;
	}

	@Override
	public ProductResponse getProductFor(String productCode) {
		Product product = productRepository.findByProductCode(productCode);
		ProductResponse response = new ProductResponse();
		if (product != null) {
			response.setCatalogCode(product.getCatalog().getCatalogCode());
			response.setDisplayName(product.getDisplayName());
			response.setPrice(product.getPrice());
			response.setProductCode(product.getProductCode());
			response.setProductDescription(product.getProductDescription());
			response.setProductName(product.getProductName());

			List<AttributeValue> attributeValues = product.getAttributeValues();			
			List<ProductAttributes> attributes = getAttributeValuesFor(attributeValues);

			List<ReviewResponse> reviewResponses = getReviewsFor(product);
			response.setReviews(reviewResponses);
			response.setAttributes(attributes);
		}
		return response;
	}

	private List<ProductAttributes> getAttributeValuesFor(List<AttributeValue> attributeValues) {
		List<ProductAttributes> attributes = new ArrayList<>();
		for (AttributeValue attributeValue : attributeValues) {			
			ProductAttributes productAttribute = new ProductAttributes();
			String attributeName = attributeValue.getAttribute().getName();
			String value = attributeValue.getValue();
			productAttribute.setAttribute(attributeName);
			productAttribute.setAttributeValue(value);
			attributes.add(productAttribute);
		}
		aggreagateSimilar(attributes);
		return attributes;
	}

	private void aggreagateSimilar(List<ProductAttributes> attributes) {
		Map<String, String> attributeMap = new HashMap<>();
		for (ProductAttributes attribute : attributes) {
			String attributeName = attribute.getAttribute();
			String attributeValue = attribute.getAttributeValue();
			if (attributeMap.containsKey(attributeName)) {
				String value = attributeMap.get(attributeName);
				attributeValue = value + ", " + attributeValue;
			} 
			attributeMap.put(attributeName, attributeValue);			
		}
		attributes.clear();
		for (Map.Entry<String, String> entry : attributeMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			ProductAttributes attribute = new ProductAttributes();
			attribute.setAttribute(key);
			attribute.setAttributeValue(value);
			attributes.add(attribute);
		}
	}

	private List<ReviewResponse> getReviewsFor(Product product) {
		List<Review> reviews = product.getReviews();
		List<ReviewResponse> reviewResponses = new ArrayList<>();
		for (Review review : reviews) {
			ReviewResponse reviewResponse = new ReviewResponse();
			reviewResponse.setReview(review.getReview());
			reviewResponse.setUserName(review.getUserName());
			reviewResponses.add(reviewResponse);
		}
		return reviewResponses;
	}

	@Override
	public String addAttributeToProduct(String productCode, String attributeName) {
		Product product = productRepository.findByProductCode(productCode);
		Attribute attribute = attributeRepository.findByName(attributeName);
		List<AttributeValue> attributeValue = attributeValueRepository.findByAttribute(attribute);
		List<AttributeValue> existing = product.getAttributeValues();
		existing.addAll(attributeValue);
		product.setAttributeValues(existing);
		productRepository.save(product);
		return "Attribute added";
	}

	@Override
	public String removeAttribute(RemoveAttribute removeAttribute) {
		Product product = productRepository.findByProductCode(removeAttribute.getProductCode());
		List<AttributeValue> attributeValues = product.getAttributeValues();
		List<String> attributesTobeRemoved = removeAttribute.getAttributeList();
		for (String attribute : attributesTobeRemoved) {
			Attribute attr = attributeRepository.findByName(attribute);
			List<AttributeValue> attrVals = attributeValueRepository.findByAttribute(attr);
			attributeValues.removeAll(attrVals);
		}
		product.setAttributeValues(attributeValues);
		productRepository.save(product);
		return "Attribute removed";
	}

}
