package com.job_application.company;

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
@RequestMapping("/companies")
public class CompanyController {

	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> findAllCompanies() {

		return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<String> createNewCompany(@RequestBody Company company) {

		companyService.createCompany(company);

		return new ResponseEntity<String>("Company created successfully", HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> findCompanyByid(@PathVariable Long id) {

		Company foundCompany = companyService.getCompanyById(id);

		if (foundCompany != null) {
			return new ResponseEntity<Company>(foundCompany, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
	 companyService.deleteCompanyById(id);

		return new ResponseEntity<>("Company with id " + id + " deleted successfully", HttpStatus.OK);
	 }


	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company company) {
		boolean isUpdated = companyService.updateCompany(id, company);

		if (isUpdated) {
			return new ResponseEntity<>("Company with id " + id + " updated successfully.", HttpStatus.OK);
		}

		return new ResponseEntity<>("Company with id " + id + " not found.", HttpStatus.NOT_FOUND);
	}
}
