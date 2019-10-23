package hr.redzicleon.application.repositories;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import hr.redzicleon.application.model.User;

@Repository
public class UserDao implements Dao<User> {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {

		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	public List<User> findAll() {
		return namedParameterJdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
	}

	public Optional<User> get(int id) {
		try {
			final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
			
			return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("SELECT * FROM users where user_id = :id",
					namedParameters, new UserRowMapper()));			
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	public Optional<User> findByUsername(String username) {
		try {
			final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("username", username);
			
			return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("SELECT * FROM users where username LIKE :username",
					namedParameters, new UserRowMapper()));			
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void save(User t) {
		String password = this.passwordEncoder.encode(t.getPassword());
		final SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("username", t.getUsername())
				.addValue("password", password)
				.addValue("name", t.getName())
				.addValue("surname", t.getSurname())
				.addValue("year_of_birth", t.getYearOfBirth())
				.addValue("email", t.getEmail())
				.addValue("companyId", t.getCompanyId());
		namedParameterJdbcTemplate.update("INSERT INTO users VALUES (default, :username, :password, true, :name, :surname,"
				+ ":year_of_birth, :email, :companyId)", namedParameters);

	}

	public void update(User t) {
		if (t.getUserId() == 0)
			return;

		final SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("user_id", t.getUserId())
				.addValue("username", t.getUsername())
				.addValue("password", t.getPassword())
				.addValue("name", t.getName())
				.addValue("surname", t.getSurname())
				.addValue("yearOfBirth", t.getYearOfBirth())
				.addValue("email", t.getEmail())
				.addValue("companyId", t.getCompanyId());
		namedParameterJdbcTemplate.update("UPDATE users SET name = :name, username = :username,"
				+ "password = :password, surname = :surname, year_of_birth = :yearOfBirth,"
				+ "email = :email, company_id = :companyId WHERE user_id = :user_id", namedParameters);
	}

	public void delete(int id) {

		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user_id", id);
		namedParameterJdbcTemplate.update("DELETE FROM company WHERE user_id = :user_id", namedParameters);

	}
}
