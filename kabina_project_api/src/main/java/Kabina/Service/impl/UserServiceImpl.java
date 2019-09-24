package Kabina.Service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Kabina.Model.User;
import Kabina.Repository.UsersRepository;
import Kabina.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public Iterable<User> findAll() {
		return usersRepository.findAll();
	}

	public void deleteUser(Long Id) {
		usersRepository.deleteByUserId(Id);;
	}

	public User addUser(User usr) {
		// TODO Auto-generated method stub
		usr.setPassword(bcryptEncoder.encode(usr.getPassword()));
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
		User user = usersRepository.findByUserId(usr.getUserId());
		if (user != null) {
			System.out.println(user.getUserName() + " from database");
			System.out.println(user.getPhone() + " from database");
			user.setEmail(usr.getEmail());
			user.setPhone(usr.getPhone());
			user.setPassword(bcryptEncoder.encode(usr.getPassword()));
			user.setShortName(usr.getShortName());
			user.setUserName(usr.getUserName());
			user.setFullName(usr.getFullName());
			System.out.println("SAVE TO DATABASE");
			usersRepository.save(user);
		}
		return user;
	}
	
	@Override
	public Map<String, String> getUserProfile(long userId) {
		User user=usersRepository.findByUserId(userId);
		long leader=user.getBusiness().getLeaderId();
		User leaderUser=usersRepository.findByUserId(leader);
		Map<String, String> map=new HashMap<String, String>();
		map.put("username",user.getUserName());
		map.put("email",user.getEmail());
		map.put("shortName",user.getShortName());
		map.put("fullName",user.getFullName());
		map.put("businessUnit",user.getBusiness().getUnitName());
		map.put("businessUnitmanager",leaderUser.getFullName());
		map.put("phone",user.getPhone());
		return map;
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return usersRepository.findByUserName(userName);
	}

}
