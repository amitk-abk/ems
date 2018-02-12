package com.target.ms.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.target.ms.ems.model.Attribute;
import com.target.ms.ems.model.AttributeValue;

@Repository("attributeValueRepository")
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
	
	List<AttributeValue> findByAttribute(Attribute attribute);
	
	AttributeValue findByAttributeAndValue(Attribute attribute, String value);
}
