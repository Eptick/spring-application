package hr.redzicleon.application.controllers;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.application.model.Company;
import hr.redzicleon.application.services.CompanyService;

@RestController()
@RequestMapping("company")
public class CompanyController {
	
	CompanyService companyService;
	
	CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@RequestMapping(method = RequestMethod.GET, value= {"", "/"})
	@ResponseBody
	public List<Company> getAllCompanies() {   
	    return this.companyService.getAllCompanies();
	}

	@ResponseBody
	@RequestMapping(value = "/{id}")
	public Company getCompany(@PathVariable("id") int companyId) {
		return this.companyService.getCompany(companyId);
	 }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Void> handleContentNotAllowedException() {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
