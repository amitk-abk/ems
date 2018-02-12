package com.target.ms.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.target.ms.ems.model.Catalog;

@Repository("catalogRepository")
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

	Catalog findByCatalogCode(String catalogCode);
}
