package com.target.ms.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.target.ms.ems.model.AttributeType;

@Repository("attributeTypeRepository")
public interface AttributeTypeRepository extends JpaRepository<AttributeType, Long> {
	AttributeType findByAttributeType(String attributeType);
}
