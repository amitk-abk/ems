package com.target.ms.ems.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.ms.ems.model.Attribute;
import com.target.ms.ems.model.AttributeType;
import com.target.ms.ems.model.AttributeValue;
import com.target.ms.ems.repository.AttributeRepository;
import com.target.ms.ems.repository.AttributeTypeRepository;
import com.target.ms.ems.repository.AttributeValueRepository;
import com.target.ms.ems.response.AttributeResponse;
import com.target.ms.ems.service.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private AttributeTypeRepository attributeTypeRepository;

	@Autowired
	private AttributeValueRepository attributeValueRepository;

	@Override
	public String createAttribute(AttributeResponse attributeBean) {
		AttributeType type = getAttributeTypeFor(attributeBean);
		Attribute attribute = attributeRepository.findByName(attributeBean.getName());
		if (attribute != null)
			return "Attribute aleady exists";
		attribute = createAttributeFor(attributeBean, type);		
		createAttributeValuesFor(attributeBean, attribute);
		return "created attribute:" + attribute.getName();
	}

	private void createAttributeValuesFor(AttributeResponse attributeBean, Attribute attribute) {
		List<String> attributeValues = attributeBean.getAttributeValues();		
		List<AttributeValue> attributeVal = new ArrayList<>();
		if (!attributeValues.isEmpty()) {
			for (String value : attributeValues) {
				AttributeValue attributeValue = new AttributeValue();
				attributeValue.setAttribute(attribute);
				attributeValue.setValue(value);
				attributeValue = attributeValueRepository.save(attributeValue);
				attributeVal.add(attributeValue);
			}
			if (!attributeVal.isEmpty()) {
				attribute.setAttributeValues(attributeVal);
				attributeRepository.save(attribute);
			}
		}
	}

	private Attribute createAttributeFor(AttributeResponse attributeBean, AttributeType type) {
		Attribute attribute = new Attribute();
		attribute.setName(attributeBean.getName());
		attribute.setDisplayName(attributeBean.getDisplayName());
		attribute.setDescription(attributeBean.getDescription());
		attribute.setEnabled(attributeBean.getEnabled());
		attribute.setSortable(attributeBean.getSortable());
		attribute.setVisible(attributeBean.getVisible());
		attribute.setType(type);		
		attribute = attributeRepository.save(attribute);
		return attribute;
	}

	private AttributeType getAttributeTypeFor(AttributeResponse attributeBean) {
		String attributeType = attributeBean.getAttributeType();
		AttributeType type = attributeTypeRepository.findByAttributeType(attributeType);
		if (type == null) {
			type = new AttributeType();
			type.setAttributeType(attributeType);
			attributeTypeRepository.save(type);
		}
		return type;
	}

	@Override
	public AttributeResponse getAttributeFor(String attributeName) {
		Attribute attribute = attributeRepository.findByName(attributeName);
		AttributeResponse attributeResponse = new AttributeResponse();
		attributeResponse.setName(attribute.getName());
		attributeResponse.setDisplayName(attribute.getDisplayName());
		attributeResponse.setDescription(attribute.getDescription());
		attributeResponse.setAttributeType(attribute.getType().getAttributeType());
		attributeResponse.setEnabled(attribute.getEnabled());
		attributeResponse.setSortable(attribute.getSortable());
		attributeResponse.setVisible(attribute.getVisible());		
		List<String> attributeValues = getAttributeValuesFor(attribute);
		attributeResponse.setAttributeValues(attributeValues);
		return attributeResponse;
	}

	private List<String> getAttributeValuesFor(Attribute attribute) {
		List<String> attributeValues = new ArrayList<>();
		for (AttributeValue attributeValue : attribute.getAttributeValues()) {
			attributeValues.add(attributeValue.getValue());
		}
		return attributeValues;
	}

	@Override
	public String updateAttributeFor(AttributeResponse attributeResponse) {
		Attribute attribute = attributeRepository.findByName(attributeResponse.getName());
		if (attribute == null)
			return "Attribute does not exists!";
		attribute.setDescription(attributeResponse.getDescription());
		attribute.setDisplayName(attributeResponse.getDisplayName());
		attribute.setEnabled(attributeResponse.getEnabled());
		attribute.setSortable(attributeResponse.getSortable());
		attribute.setVisible(attributeResponse.getVisible());
		getAttributeTypeFor(attributeResponse, attribute);
		updateAttributeValuesFor(attributeResponse, attribute);
		attributeRepository.save(attribute);
		return "Updated attribute for entity";
	}

	private void updateAttributeValuesFor(AttributeResponse attributeResponse, Attribute attribute) {
		List<String> attributeValues = attributeResponse.getAttributeValues();
		List<AttributeValue> values = attribute.getAttributeValues();
		for (int i = 0; i < values.size(); i++) {
			AttributeValue av = values.get(i);
			String newValue = attributeValues.get(i);
			if (!av.getValue().equals(newValue)) {
				av.setValue(newValue);
			}
		}
		attribute.setAttributeValues(values);
	}

	private void getAttributeTypeFor(AttributeResponse attributeResponse, Attribute attribute) {
		AttributeType attributeType = attributeTypeRepository.findByAttributeType(attributeResponse.getAttributeType());
		attribute.setType(attributeType);
	}

	@Override
	public List<AttributeResponse> getAllAttributes() {
		List<AttributeResponse> attributeResponseList = new ArrayList<>();
		List<Attribute> attributes = attributeRepository.findAll();
		for (Attribute attribute : attributes) {
			AttributeResponse response = getAttributeFor(attribute.getName());
			attributeResponseList.add(response);
		}
		return attributeResponseList;
	}

}
