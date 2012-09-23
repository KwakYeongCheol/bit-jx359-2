package kr.co.webcash.web.validator;

import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegisterValidator implements Validator{
	
	@Autowired private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		if(user != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "field.required");

			String loginId = user.getLoginId();
			if(loginId != null){
				if(userService.findByLoginId(loginId) != null)	errors.rejectValue("loginId", "field.duplicate.user.loginId");
			}
			
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
			
			String password = user.getPassword(); 
			if(password != null){
				if(password.length() < 8)	errors.rejectValue("password", "field.minlength.user.password");
			}
		}
		
	}

}
