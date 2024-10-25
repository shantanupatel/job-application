package com.job_application.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
		reviewService.createReview(companyId, review);

		return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
		Review foundReview = reviewService.getReviewById(companyId, reviewId);

		if (foundReview != null) {
			return new ResponseEntity<>(foundReview, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
		// reviewService.deleteReviewById(reviewId);
		//
		// return new ResponseEntity<>("Review with id " + reviewId + " deleted
		// successfully", HttpStatus.OK);

		boolean isReviewDeleted = reviewService.deleteReviewById(companyId, reviewId);

		if (isReviewDeleted) {
			return new ResponseEntity<>("Review with id " + reviewId + " deleted successfully", HttpStatus.OK);
		}

		return new ResponseEntity<>("Review with id " + reviewId + " not found", HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId,
			@RequestBody Review review) {
		Boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);

		if (isUpdated) {
			return new ResponseEntity<>("Review with id " + reviewId + " updated successfully.", HttpStatus.OK);
		}

		return new ResponseEntity<>("Review with id " + reviewId + " not found.", HttpStatus.NOT_FOUND);
	}

}
