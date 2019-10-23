package hr.redzicleon.application.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
 
	/**
	 * Method returns only the 401 status and not the whole page
	 */
    public void commence(
        HttpServletRequest request, 
        HttpServletResponse response, 
        AuthenticationException authException) throws IOException {
         
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }


}
