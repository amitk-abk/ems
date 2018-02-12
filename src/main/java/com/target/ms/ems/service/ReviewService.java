package com.target.ms.ems.service;

import org.springframework.transaction.annotation.Transactional;

import com.target.ms.ems.response.ReviewResponse;

public interface ReviewService {

	@Transactional
	String submitReview(String productCode, ReviewResponse reviewResponse);

}
