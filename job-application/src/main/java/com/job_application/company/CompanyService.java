package com.job_application.company;

import java.util.List;

public interface CompanyService {

	List<Company> getAllCompanies();

	void createCompany(Company company);

	Company getCompanyById(Long id);

	void deleteCompanyById(Long id);

	boolean updateCompany(Long id, Company company);

}
