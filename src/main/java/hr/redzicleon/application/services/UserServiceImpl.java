package hr.redzicleon.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hr.redzicleon.application.factories.UserFactory;
import hr.redzicleon.application.model.User;
import hr.redzicleon.application.repositories.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	UserDao userDao;
	UserFactory userFactory;
	
	public UserServiceImpl(UserDao userDao, UserFactory userFactory) {
		this.userDao = userDao;
		this.userFactory = userFactory;
	}

	public List<User> getAllUsers () {
		return this.userDao.findAll();
	}
	
	public List<UserDTO> getAllUsersWithCompanyName () {
		return this.userDao.findAllWithCompanyName();
	}

	public User getUser(int id) {
		Optional<User> user = this.userDao.get(id);
		return user.get();
	}

	public void deleteUser(int id) {
		this.userDao.delete(id);
		
	}

	public boolean addNewUser(User user) {
		this.userDao.save(user);
		return true;
	}

	public User updateUserInformation(User user) {
		this.userDao.update(user);
		return user;
	}
	

}
