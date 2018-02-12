package com.target.ms.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.target.ms.ems.model.Attribute;

@Repository("attributeRepository")
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

	Attribute findByName(String name);
}
