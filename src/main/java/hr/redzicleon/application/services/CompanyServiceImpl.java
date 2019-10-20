package hr.redzicleon.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import hr.redzicleon.application.factories.CompanyFactory;
import hr.redzicleon.application.model.Company;
import hr.redzicleon.application.repositories.CompanyDao;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	CompanyDao companyDao;
	CompanyFactory companyFactory;
	
	public CompanyServiceImpl(CompanyDao companyDao, CompanyFactory companyFactory) {
		this.companyDao = companyDao;
		this.companyFactory = companyFactory;
	}

	public List<Company> getAllCompanies() {
		return this.companyDao.findAll();
	}

	public boolean addNewCompany(String companyName) {
		Company company = this.companyFactory.getNewCompany();
		company.setName(companyName);
		this.companyDao.save(company);
		return true;
	}

	public Company getCompany(int id) throws EmptyResultDataAccessException {
		Optional<Company> company = this.companyDao.get(id);
		return company.get();
	}

	public void deleteCompany(int id) {
		this.companyDao.delete(id);
	}

	public Company updateCompanyInformation(Company company) {
		this.companyDao.update(company);
		return company;
	}

}
