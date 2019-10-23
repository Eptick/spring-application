package hr.redzicleon.application.services;

import java.util.List;

import hr.redzicleon.application.model.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User getUser(int id);
	
	public void deleteUser(int id);
	
	public boolean addNewUser(User user);
	
	public User updateUserInformation(User user);
	
	public List<UserDTO> getAllUsersWithCompanyName();
	
}
