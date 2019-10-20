package hr.redzicleon.application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.redzicleon.application.model.Company;

public class CompanyRowMapper implements RowMapper<Company> {
	
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company();
 
		company.setId(rs.getInt("id"));
        company.setName(rs.getString("company_name"));
 
        return company;
    }
	
}
