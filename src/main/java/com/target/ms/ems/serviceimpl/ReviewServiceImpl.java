package com.target.ms.ems.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.ms.ems.model.Product;
import com.target.ms.ems.model.Review;
import com.target.ms.ems.repository.ProductRepository;
import com.target.ms.ems.repository.ReviewRepository;
import com.target.ms.ems.response.ReviewResponse;
import com.target.ms.ems.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public String submitReview(String productCode, ReviewResponse reviewResponse) {
		Product product = productRepository.findByProductCode(productCode);
		Review review = new Review();
		review.setProduct(product);
		review.setReview(reviewResponse.getReview());
		review.setUserName(reviewResponse.getUserName());
		reviewRepository.save(review);
		return "Review submitted successfully";
	}

}
