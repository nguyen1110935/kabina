package Kabina.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import Kabina.Model.User;

@Component
public class UsersValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		if (!user.getUserName().matches("^[a-zA-Z0-9]{8,15}")) {
			errors.rejectValue("userName", "not accept Special letter");
		}
//		if (!user.getPassword().matches("^\\w{6,20}$")) {
//			errors.rejectValue("password", "password not less than 6 and greater than 20");
//		}
	}

}
