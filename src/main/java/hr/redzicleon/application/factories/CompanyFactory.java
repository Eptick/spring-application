package hr.redzicleon.application.factories;

import org.springframework.stereotype.Component;

import hr.redzicleon.application.model.Company;

@Component
public class CompanyFactory {
	
	public Company getNewCompany() {
		return new Company();
	}

}
