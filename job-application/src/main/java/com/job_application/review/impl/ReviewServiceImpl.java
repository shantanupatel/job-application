package com.job_application.review.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.job_application.exception.IdNotFoundException;
import com.job_application.review.Review;
import com.job_application.review.ReviewRepository;
import com.job_application.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		// return reviewRepository.findAll();
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);

		return reviews;
	}

	@Override
	public void createReview(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public Review getReviewById(Long id) {
		return reviewRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteReviewById(Long id) {
		reviewRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Review with id " + id + " not found"));

		reviewRepository.deleteById(id);
	}

	@Override
	public boolean updateReview(Long id, Review updatedReview) {
		Optional<Review> optionalReview = reviewRepository.findById(id);

		if (optionalReview.isPresent()) {
			Review review = optionalReview.get();

			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			review.setCompany(updatedReview.getCompany());

			reviewRepository.save(review);

			return true;
		}

		return false;
	}

}
