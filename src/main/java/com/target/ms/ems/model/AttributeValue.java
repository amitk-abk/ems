package com.target.ms.ems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "attributevalue")
public class AttributeValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attributeValueId;

	@ManyToOne
	private Attribute attribute;

	private String value;

	public long getAttributeValueId() {
		return attributeValueId;
	}

	public void setAttributeValueId(long attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
