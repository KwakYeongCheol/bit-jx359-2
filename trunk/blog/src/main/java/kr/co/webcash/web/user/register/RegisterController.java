package kr.co.webcash.web.user.register;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.UserRegisterValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
@RequestMapping("/user/register")
public class RegisterController {
	
	@Autowired private UserService userService;
	@Autowired private UserRegisterValidator validator;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().loginUser();
	}
	
	@RequestMapping("/step01")
	public String step01(){
		return "redirect:/user/register/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void showRegisterForm(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user == null)	user = new User();
		
		model.addAttribute("user", user);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user, BindingResult result, Model model, SessionStatus status){
		this.validator.validate(user, result);
		
		if(!result.hasErrors()){
			if(userService.save(user)){
				status.setComplete();
				
				this.loginUserProvider.get().save(user);
				
				return "redirect:/blog/settings";
			}
		}
		
		return "/user/register/register";
	}
}