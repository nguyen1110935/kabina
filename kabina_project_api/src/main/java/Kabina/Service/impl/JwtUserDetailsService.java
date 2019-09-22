package Kabina.Service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Kabina.Model.Role;

import Kabina.Model.User;
import Kabina.Repository.RoleRepository;
import Kabina.Repository.UsersRepository;
import Kabina.DTO.SecurityUser;
import Kabina.DTO.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usersRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new SecurityUser(user.getUserId(),user.getUserName(), user.getPassword(), user.getRole().getRoleName());
	}

	public boolean save(UserDTO user){ 
		User newUser = new User();
		newUser.setUserName(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

		Role userRole;
		if("User".equals(user.getRole()))
			userRole = roleRepo.findByRoleId((long)2);
		else
			userRole = roleRepo.findByRoleId((long)1);

		newUser.setRole(userRole);

		usersRepository.save(newUser);
		return true;
	}

}