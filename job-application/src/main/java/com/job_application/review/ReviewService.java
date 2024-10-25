package com.job_application.review;

import java.util.List;

public interface ReviewService {

	List<Review> getAllReviews(Long companyId);

	void createReview(Long companyId, Review review);

	Review getReviewById(Long companyId, Long reviewId);

	boolean deleteReviewById(Long companyId, Long reviewId);

	boolean updateReview(Long companyId, Long reviewId, Review review);
}
