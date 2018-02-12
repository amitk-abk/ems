package com.target.ms.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.target.ms.ems.response.ReviewResponse;
import com.target.ms.ems.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	private ReviewService reviewService;
	
	@Autowired
	public ReviewController (ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PostMapping("/review/{productCode}")
	public String submitReview(@PathVariable String productCode, @RequestBody ReviewResponse reviewResponse) {
		String response = reviewService.submitReview(productCode, reviewResponse);
		logger.info("Submitted review for product {} by user {}", productCode, reviewResponse.getUserName());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String operationException(Exception e) {
		logger.error("Error occured in processing the request. Message : {}", e.getMessage(), e);
		return "Something went wrong in processing the request, kindly check the payload or request";
	}
}
