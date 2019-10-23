package hr.redzicleon.application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import hr.redzicleon.application.model.User;

public class UserRowMapper implements RowMapper<User> {
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException, EmptyResultDataAccessException {
		User user = new User();
 
		user.setUserId(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setYearOfBirth(rs.getInt("year_of_birth"));
		user.setEmail(rs.getString("email"));
        user.setCompanyId(rs.getInt("company_id"));
 
        return user;
    }
	
}
