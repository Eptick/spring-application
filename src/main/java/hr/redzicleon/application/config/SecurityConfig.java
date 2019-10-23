package hr.redzicleon.application.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import hr.redzicleon.application.config.auth.AuthSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "hr.redzicleon.application.config" })
@ComponentScan(basePackages = { "hr.redzicleon.application.config.auth" })
public class SecurityConfig  extends WebSecurityConfigurerAdapter { // Could this extend be causing problems, explore it

	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	/**
	 * Makes the authentification connect to the database using the Datasource
	 * Selects the correct data from the users table
	 * Mocs the role to USER in the database
	 * Sets the password encoder to BCrypt
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT username, password, enabled from users where username = ?")
		.authoritiesByUsernameQuery("SELECT username, \"USER\" from users where username = ?")
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/**
	 * disable the csrf
	 * set the entry point to a custom one
	 * require auth for /user and /company endpoints
	 * set the login page
	 * and add the success handler and failed handler
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
	    http
	    .csrf().disable()
	    .cors().and()
	    .exceptionHandling()
	    .authenticationEntryPoint(authEntryPoint)
	    .and()
	    .authorizeRequests()
	    .antMatchers("/user/**").authenticated()
	    .antMatchers("/company/**").authenticated()
	    .antMatchers(HttpMethod.OPTIONS).permitAll()
	    .and()
	    .formLogin()
	    .loginPage("/login")
	    .permitAll()
	    .loginProcessingUrl("/login")
	    .defaultSuccessUrl("/")
	    .successHandler(new AuthSuccessHandler())
	    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
	    .and()
	    .httpBasic()
	    .and()
	    .logout();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
