package com.target.ms.ems.response;

import java.util.ArrayList;
import java.util.List;

public class RemoveAttribute {

	private String productCode;
	private List<String> attributeList = new ArrayList<>();
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public List<String> getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(List<String> attributeList) {
		this.attributeList = attributeList;
	}	
	
}
