package com.target.ms.ems.response;

public class ReviewResponse {
	
	private String review;
	private String userName = "anonymous";	

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
