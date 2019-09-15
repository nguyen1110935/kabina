package Kabina;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Kabina.Model.User;
import Kabina.Repository.UsersRepository;
import Kabina.Service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersTest {
	private static final String USER_NAME = "test";
	private static final String PASS = "test";
	private static final boolean ISACTIVE = true;
	private static final boolean ISADMIN = true;
	@Mock
	UsersRepository repository;
	@Mock
	User u;

	@InjectMocks
	UserServiceImpl service;

//	@Before
//	public void setUp() {
//		u = new Users();
//		u.setActive(ISACTIVE);
//		u.setAdmin(ISADMIN);
//		u.setId(Long.parseLong("1"));
//		u.setIdTicket(1);
//		u.setUserName(USER_NAME);
//		u.setPassword(PASS);
//	}
//
//	@Test
//	public void add() {
//		Users user = new Users();
//		user.setActive(false);
//		user.setAdmin(true);
//		user.setId(Long.parseLong("1"));
//		user.setIdTicket(1);
//		user.setUserName(USER_NAME);
//		user.setPassword(PASS);
//		when(repository.saveAndFlush(user)).thenReturn(u);
//		Users us = service.add(USER_NAME, PASS);
//		Assert.assertEquals(USER_NAME, us.getUserName());
//	}
//
//	@Test
//	public void update() {
//		Users user = new Users();
//		user.setActive(ISACTIVE);
//		user.setAdmin(ISADMIN);
//		user.setId(Long.parseLong("1"));
//		user.setIdTicket(1);
//		user.setUserName(USER_NAME);
//		user.setPassword(PASS);
//		when(repository.findByUserName(USER_NAME)).thenReturn(u);
//		when(repository.saveAndFlush(user)).thenReturn(u);
//		Users us = service.update(USER_NAME, ISADMIN, ISACTIVE);
//		Assert.assertEquals(ISACTIVE, us.isActive());
//		Assert.assertEquals(ISADMIN, us.isAdmin());
//	}
//
//	@Test
//	public void login() {
//		Users user = new Users();
//		user.setActive(ISACTIVE);
//		user.setAdmin(ISADMIN);
//		user.setId(Long.parseLong("1"));
//		user.setIdTicket(1);
//		user.setUserName(USER_NAME);
//		user.setPassword(PASS);
//		when(repository.findByUserNameAndPassword(USER_NAME, PASS)).thenReturn(u);
//		Users us = service.login(USER_NAME, PASS);
//		Assert.assertEquals(USER_NAME, us.getUserName());
//		Assert.assertEquals(PASS, us.getPassword());
//	}

}
