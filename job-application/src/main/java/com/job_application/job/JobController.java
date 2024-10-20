package com.job_application.job;

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
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	// get all jobs
	@GetMapping
	public ResponseEntity<List<Job>> findAll() {
		// alternate way
		// return ResponseEntity.ok(jobService.findAll());

		return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
	}

	// create a new job
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {

		jobService.createJob(job);

		return new ResponseEntity<String>("Job created successfully", HttpStatus.CREATED);

	}

	// get a specific job using job id
	@GetMapping("/{id}")
	public ResponseEntity<Job> findJobById(@PathVariable Long id) {
		Job foundJob = jobService.getJobById(id);

		if (foundJob != null) {
			return new ResponseEntity<Job>(foundJob, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// update an existing job via job id
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
		boolean isUpdated = jobService.updateJob(id, job);

		if (isUpdated) {
			return new ResponseEntity<>("Job details for job id " + id + " updated successfully.", HttpStatus.OK);
		}

		return new ResponseEntity<>("Job details for job id " + id + " not found.", HttpStatus.NOT_FOUND);
	}

	// delete an existing job via job id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
		jobService.deleteJobById(id);

		return new ResponseEntity<>("Job with id " + id + " deleted successfully", HttpStatus.OK);
	}
}
