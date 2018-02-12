package com.target.ms.ems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "attributetype")
public class AttributeType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long typeId;
	
	//single value, free text, select etc
	private String attributeType;
	
	//private String attributeDataType;

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	
	
}
