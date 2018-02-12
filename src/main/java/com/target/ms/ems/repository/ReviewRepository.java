package com.target.ms.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.target.ms.ems.model.Product;
import com.target.ms.ems.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByProduct(Product product);
}
