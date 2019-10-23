package hr.redzicleon.application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import hr.redzicleon.application.model.User;
import hr.redzicleon.application.services.UserDTO;

public class UserDtoRowMapper implements RowMapper<UserDTO> {
	
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException, EmptyResultDataAccessException {
		UserDTO user = new UserDTO();
 
		user.setUserId(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setYearOfBirth(rs.getInt("year_of_birth"));
		user.setEmail(rs.getString("email"));
        user.setCompanyId(rs.getInt("company_id"));
        user.setCompanyName(rs.getString("company_name"));

        return user;
    }
	
}
