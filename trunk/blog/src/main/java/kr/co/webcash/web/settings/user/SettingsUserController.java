package kr.co.webcash.web.settings.user;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.UserService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/settings/user")
public class SettingsUserController {
	
	@Autowired private UserService userService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping()
	public String userHome(){
		return "/settings/user/home";
	}
	
	@ModelAttribute
	public User loginUser(){
		return loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public void changePasswordForm(){
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestParam String password, @RequestParam String newPassword, @RequestParam String newPasswordConfirm){
		
		if(userService.checkLoginIdAndPassword(loginUser().getLoginId(), password)){
			
			if(newPassword != null && newPassword.length() > 7 && newPassword.equals(newPasswordConfirm)){
				User user = new User();
				user.setLoginId(loginUser().getLoginId());
				user.setPassword(newPassword);
				
				userService.update(user);
				
				return "redirect:/settings/user";
			}
		}
		
		return "/settings/user/changePassword";
	}
	
	@RequestMapping("/withdraw")
	public void withdraw(){
		
	}

}
