package hr.redzicleon.application.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Initialises the spring security
 * https://docs.spring.io/spring-security/site/docs/5.2.0.RELEASE/reference/htmlsingle/#abstractsecuritywebapplicationinitializer
 * @author leon
 *
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityInitializer() {
		super(SecurityConfig.class);
	}
}
