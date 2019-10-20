package hr.redzicleon.application.factories;

import org.springframework.stereotype.Component;

import hr.redzicleon.application.model.User;

@Component
public class UserFactory {

	public User getNewUser() {
		return new User();
	}
}
