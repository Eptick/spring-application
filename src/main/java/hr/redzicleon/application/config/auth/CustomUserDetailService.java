package hr.redzicleon.application.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.redzicleon.application.model.User;
import hr.redzicleon.application.repositories.UserDao;

/**
 * User service to find in users table by username
 * @author leon
 *
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
    private UserDao userDao;
 

    public UserDetails loadUserByUsername(String username) {
    	Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserPrincipal(user.get());
    }
}
