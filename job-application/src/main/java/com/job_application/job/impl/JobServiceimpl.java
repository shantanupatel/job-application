package com.job_application.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.job_application.exception.IdNotFoundException;
import com.job_application.job.Job;
import com.job_application.job.JobRepository;
import com.job_application.job.JobService;

@Service
public class JobServiceimpl implements JobService {

	private JobRepository jobRepository;

	public JobServiceimpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteJobById(Long id) {
		Job job = jobRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Job with provided id " + id + " not found"));

		jobRepository.deleteById(id);

	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);

		if (jobOptional.isPresent()) {
			Job job = jobOptional.get();

			job.setName(updatedJob.getName());
			job.setDescription(updatedJob.getDescription());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setLocation(updatedJob.getLocation());

			jobRepository.save(job);

			return true;
		}

		return false;
	}

}
