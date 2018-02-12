package com.target.ms.ems.response;

import java.util.ArrayList;
import java.util.List;

public class AttributeResponse {

	private String name;

	private String displayName;

	private String description;

	// UI display rules : sortable, visible, enabled etc.
	private int sortable;

	private int visible;

	private int enabled;

	private String attributeType;

	private List<String> attributeValues = new ArrayList<>();

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

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public List<String> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<String> attributeValues) {
		this.attributeValues = attributeValues;
	}

	/*
	 * public Set<String> getAttributeValues() { return attributeValues; }
	 * 
	 * public void setAttributeValues(Set<String> attributeValues) {
	 * this.attributeValues = attributeValues; }
	 */

}
