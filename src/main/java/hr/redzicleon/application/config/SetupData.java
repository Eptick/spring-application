package hr.redzicleon.application.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import hr.redzicleon.application.factories.UserFactory;
import hr.redzicleon.application.model.User;
import hr.redzicleon.application.repositories.UserDao;

@Component
public class SetupData {
	
	private UserDao userDao;
	private UserFactory userFactory;
	
	public SetupData(UserDao userDao,UserFactory userFactory, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.userFactory = userFactory;
		this.init();
	}
	 
	public void init() {
		if(this.userDao.get(1).isEmpty()) {
			User customUser = this.userFactory.getNewUser();
			customUser.setUsername("redzicleon");
			customUser.setPassword("mojasigurnalozinka");
			customUser.setName("redzicleon");
			customUser.setSurname("redzicleon");
			customUser.setYearOfBirth(1995);
			customUser.setEmail("redzicleon@gmail.com");
			customUser.setCompanyId(1);
			userDao.save(customUser);
		}
	}

}
