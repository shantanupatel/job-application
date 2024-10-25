package com.job_application.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.job_application.company.Company;
import com.job_application.company.CompanyService;
import com.job_application.exception.IdNotFoundException;
import com.job_application.review.Review;
import com.job_application.review.ReviewRepository;
import com.job_application.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	private CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		super();
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);

		return reviews;
	}

	@Override
	public void createReview(Long companyId, Review review) {
		Company company = companyService.getCompanyById(companyId);

		if (company != null) {
			review.setCompany(company);

			reviewRepository.save(review);
		} else {
			throw new IdNotFoundException("Company with id " + companyId + " not found");
		}

	}

	@Override
	public Review getReviewById(Long companyId, Long reviewId) {
		// call the getAllReviews method by passing in companyId as parameter to avoid
		// code duplication.
		List<Review> reviews = getAllReviews(companyId);

		if (reviews.isEmpty()) {
			throw new IdNotFoundException("Company with id " + companyId + " not found");
		}

		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public boolean deleteReviewById(Long companyId, Long reviewId) {
		// reviewRepository.findById(id).orElseThrow(() -> new
		// IdNotFoundException("Review with id " + id + " not found"));
		//
		// reviewRepository.deleteById(id);

		if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
			Review review = reviewRepository.findById(reviewId).orElse(null);
			Company company = review.getCompany();

			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.updateCompany(companyId, company);
			reviewRepository.deleteById(reviewId);

			return true;
		}

		return false;
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		if (companyService.getCompanyById(companyId) != null) {
			updatedReview.setCompany(companyService.getCompanyById(companyId));
			updatedReview.setId(reviewId);

			reviewRepository.save(updatedReview);

			return true;
		}

		return false;

	}

}
