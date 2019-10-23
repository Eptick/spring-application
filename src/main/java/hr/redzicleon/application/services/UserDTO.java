package hr.redzicleon.application.services;

import hr.redzicleon.application.model.User;

public class UserDTO extends User {
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
