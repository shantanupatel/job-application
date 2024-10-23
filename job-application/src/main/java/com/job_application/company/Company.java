package com.job_application.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job_application.job.Job;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;

	// JsonIgnore will remove recursive callbacks (infinite loop)
	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Job> jobs;

	// private List<Review> reviews;

	public Company() {
		super();
	}

	public Company(Long id, String name, String description, List<Job> jobs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.jobs = jobs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

}
