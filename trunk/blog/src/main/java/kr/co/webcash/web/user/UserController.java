package kr.co.webcash.web.user;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.UserRegisterValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private UserRegisterValidator validator;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().loginUser();
	}
	
	@RequestMapping("/home")
	public void Main(Model model){
		User loginUser = this.loginUserProvider.get().loginUser();
		User user = userService.findByLoginId(loginUser.getLoginId());
		model.addAttribute("user", user);
	}

	@RequestMapping(value="/modify")
	public void modify(Model model){
		User user = userService.findByLoginId(loginUser().getLoginId());
		
		model.addAttribute("user", user);
	}
	@RequestMapping(value="/modifyAction", method=RequestMethod.POST)
	public String modifyAction(@RequestParam String password,@RequestParam String newPassword, Model model){
		if(userService.checkLoginIdAndPassword(loginUser().getLoginId(), password)){
			User user = new User();
			user.setLoginId(loginUser().getLoginId());
			user.setPassword(newPassword);
			userService.update(user);
			return "redirect:/user/home";			
		}
		
		return "redirect:/user/modify";
	}
}