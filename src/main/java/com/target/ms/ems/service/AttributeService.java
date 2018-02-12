package com.target.ms.ems.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.target.ms.ems.response.AttributeResponse;

public interface AttributeService {

	@Transactional
	String createAttribute(AttributeResponse attributeBean);

	@Transactional(readOnly = true)
	AttributeResponse getAttributeFor(String attributeName);

	@Transactional
	String updateAttributeFor(AttributeResponse attributeResponse);

	@Transactional(readOnly = true)
	List<AttributeResponse> getAllAttributes();

}
