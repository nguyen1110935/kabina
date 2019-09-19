package Kabina.Service;

import java.util.Map;

import Kabina.Model.User;

public interface UserService {
	Iterable<User> findAll();
	public User addUser(User usr);
	public User updateUser(User usr);
	public void deleteUser (Integer Id);
	int checkUserExist(String userName);
	Map<String, String> getUserProfile(long userId);
}
