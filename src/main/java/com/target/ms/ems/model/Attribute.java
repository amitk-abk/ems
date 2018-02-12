package com.target.ms.ems.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "attribute")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attributeId;

	// private String attributeCode;

	private String name;

	private String displayName;

	private String description;

	// UI display rules : sortable, visible, enabled etc.
	private int sortable;

	private int visible;

	private int enabled;

	@OneToOne
	private AttributeType type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute", cascade = CascadeType.ALL)
	private List<AttributeValue> attributeValues = new ArrayList<>();

	public long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

	public int getSortable() {
		return sortable;
	}

	public void setSortable(int sortable) {
		this.sortable = sortable;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

	/*
	 * public Set<AttributeValue> getAttributeValues() { return attributeValues; }
	 * 
	 * public void setAttributeValues(Set<AttributeValue> attributeValues) {
	 * this.attributeValues = attributeValues; }
	 * 
	 * public String getAttributeCode() { return attributeCode; }
	 * 
	 * public void setAttributeCode(String attributeCode) { this.attributeCode =
	 * attributeCode; }
	 */
}
