package com.target.ms.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.target.ms.ems.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByProductCode(String productCode);
}
