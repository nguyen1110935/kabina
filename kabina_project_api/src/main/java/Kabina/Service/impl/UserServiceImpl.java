package Kabina.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.User;
import Kabina.Repository.UsersRepository;
import Kabina.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersRepository usersRepository;

	public Iterable<User> findAll() {
		return usersRepository.findAll();
	}

	public void deleteUser(Integer Id) {
		usersRepository.deleteByUserId(Id);;
	}

	public User addUser(User usr) {
		// TODO Auto-generated method stub
		usersRepository.save(usr);
		return usr;
	}

	public int checkUserExist(String userName) {
		User user = usersRepository.findByUserName(userName);
		if (user != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public User updateUser(User usr) {
		// TODO Auto-generated method stub
		System.out.println(usr.getUserName() + " from url");
		System.out.println(usr.getPhone() + " from url");
		User user = usersRepository.findByUserId((int) usr.getUserId());
		if (user != null) {
			System.out.println(user.getUserName() + " from database");
			System.out.println(user.getPhone() + " from database");
			user.setEmail(usr.getEmail());
			user.setPhone(usr.getPhone());
			user.setPassword(usr.getPassword());
			user.setShortName(usr.getShortName());
			user.setUserName(usr.getUserName());

			System.out.println("SAVE TO DATABASE");
			usersRepository.save(user);
		}
		return user;
	}
}
