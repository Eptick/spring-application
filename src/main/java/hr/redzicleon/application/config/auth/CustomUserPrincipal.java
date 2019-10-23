package hr.redzicleon.application.config.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hr.redzicleon.application.model.User;

@SuppressWarnings("serial")
public class CustomUserPrincipal implements UserDetails {

	private User user;

	public CustomUserPrincipal(User user) {
		this.user = user;
	}

	/**
	 * The role of the user is always user for this case
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
		return authorities;
	}

	public String getUsername() {
		return user.getUsername();
	}

	public String getPassword() {
		return user.getPassword();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

}
