package Kabina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Kabina.Model.Booking;
import Kabina.Model.Shelf;
import Kabina.Model.User;
import Kabina.Repository.BookingRepository;
import Kabina.Repository.BookingtempRepository;
import Kabina.Repository.FloorRepository;
import Kabina.Repository.ShelfRepository;
import Kabina.Repository.UnitRepository;
import Kabina.Repository.UsersRepository;
import Kabina.Service.impl.UserServiceImpl;
import Kabina.Validator.UsersValidator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/control")
public class UsersController {

	@Autowired
	ShelfRepository shelfRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	BookingtempRepository bookingtempRepository;
	
	@Autowired
	FloorRepository floorRepository;
	
	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	private UserServiceImpl usersService;

	@Autowired
	private UsersValidator usersValidator;
	
	@Autowired
	private UsersRepository usersRepository;

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
	
	@Transactional
	@RequestMapping(value = "/del/{Id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long Id) {
		usersService.deleteUser(Id);
	}
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Booking> get() {
		return (List<Booking>) bookingRepository.findAll();
	}
	
	@RequestMapping(value = "/getUsers/{userName}", method = RequestMethod.GET)
	public User getUserById(@PathVariable String userName) {
		return usersRepository.findByUserName(userName);
	}
	
	@RequestMapping(value = "/usersProfile/{userId}", method = RequestMethod.GET)
	public Map<String, String> getUserProfile(@PathVariable long userId) {
		return usersService.getUserProfile(userId);
	}
}
