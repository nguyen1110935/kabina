package Kabina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Kabina.Model.User;
import Kabina.Service.impl.UserServiceImpl;
import Kabina.Validator.UsersValidator;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/control")
public class UsersController {

	@Autowired
	private UserServiceImpl usersService;

	@Autowired
	private UsersValidator usersValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(usersValidator);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return (List<User>) usersService.findAll();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user) {
		
		int check = usersService.checkUserExist(user.getUserName());
		if (check != 1) {
			user = usersService.addUser(user);
			if (user != null) {
				System.out.println("A new user is added");
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				System.out.println("A new user is NOT added");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		else {
			System.out.println("There is an existing user name");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<User> update(@RequestBody User user) {
		user = usersService.updateUser(user);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/del/{Id}", method = RequestMethod.GET)
	public void deleteUser(@PathVariable Integer Id) {
		usersService.deleteUser(Id);
	}
}
