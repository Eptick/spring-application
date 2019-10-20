package hr.redzicleon.application.services;

import java.util.List;

import hr.redzicleon.application.model.Company;

public interface CompanyService {

	public List<Company> getAllCompanies();
	
	public Company getCompany(int id);
	
	public void deleteCompany(int id);
	
	public boolean addNewCompany(String companyName);
	
	public Company updateCompanyInformation(Company company);
	
}
