package com.job_application.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.job_application.company.Company;
import com.job_application.company.CompanyRepository;
import com.job_application.company.CompanyService;
import com.job_application.exception.IdNotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public void createCompany(Company company) {
		String companyName = company.getName();
		Optional<Company> optionalCompany = companyRepository.findByName(companyName);

		if (optionalCompany.isPresent()) {
			throw new RuntimeException("Category with name " + companyName + " already exists.");
		}

		companyRepository.save(company);
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCompanyById(Long id) {
		companyRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Company with provided id " + id + " not found."));

		companyRepository.deleteById(id);
	}

	@Override
	public boolean updateCompany(Long id, Company updatedCompany) {
		Optional<Company> optionalCompany = companyRepository.findById(id);

		if (optionalCompany.isPresent()) {
			Company company = optionalCompany.get();

			company.setName(updatedCompany.getName());
			company.setDescription(updatedCompany.getDescription());
			company.setJobs(updatedCompany.getJobs());

			companyRepository.save(company);

			return true;
		}

		return false;

	}

}
