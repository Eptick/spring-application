package hr.redzicleon.application.repositories;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import hr.redzicleon.application.model.Company;

@Repository
public class CompanyDao implements Dao<Company> {
	

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        
    }
    
    public List<Company> findAll() {
    	return namedParameterJdbcTemplate.query("SELECT * FROM company", new CompanyRowMapper());
    }
    
    public int addCompanny(final String name) {
    	final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", name);
        return namedParameterJdbcTemplate.update("INSERT INTO company VALUES (default, :name)", namedParameters);
    }

	public Optional<Company> get(int id) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("SELECT * FROM company where id = :id", namedParameters, new CompanyRowMapper()));
    }

	public void save(Company t) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", "default")
				.addValue("name", t.getName());
        namedParameterJdbcTemplate.update("INSERT INTO company VALUES (:id, :name)", namedParameters);
		
	}

	public void update(Company t) {
		if(t.getId() == null) return;
		
		final SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", t.getId())
				.addValue("name", t.getName());
        namedParameterJdbcTemplate.update("UPDATE company SET name = :name WHERE id = :id", namedParameters);
	}

	public void delete(int id) {
		
		final SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM company WHERE id = :id", namedParameters);
		
	}

}
