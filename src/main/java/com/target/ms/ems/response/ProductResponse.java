package com.target.ms.ems.response;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

	private String productName;

	private String displayName;

	private String productCode;

	private String productDescription;

	private String catalogCode;

	private double price;

	// private Set<ProductAttributes> attributes = new HashSet<>();
	private List<ProductAttributes> attributes = new ArrayList<>();

	private List<ReviewResponse> reviews = new ArrayList<>();

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	/*
	 * public Set<ProductAttributes> getAttributes() { return attributes; }
	 * 
	 * public void setAttributes(Set<ProductAttributes> attributes) {
	 * this.attributes = attributes; }
	 */

	public double getPrice() {
		return price;
	}

	public List<ProductAttributes> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttributes> attributes) {
		this.attributes = attributes;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<ReviewResponse> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewResponse> reviews) {
		this.reviews = reviews;
	}

}
