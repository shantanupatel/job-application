package com.job_application.review;

import java.util.List;

public interface ReviewService {

	List<Review> getAllReviews(Long companyId);

	void createReview(Review review);

	Review getReviewById(Long id);

	void deleteReviewById(Long id);

	boolean updateReview(Long id, Review review);
}
