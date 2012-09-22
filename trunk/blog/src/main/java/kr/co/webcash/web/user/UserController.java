package kr.co.webcash.web.user;

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
		User user = userService.findLoginId(loginUser.getLoginId());
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/register/step01")
	public String step01(){
		return "redirect:/user/register/register";
	}
	
	@RequestMapping(value="/register/register", method=RequestMethod.GET)
	public void showRegisterForm(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user == null)	user = new User();
		
		model.addAttribute("user", user);
	}
	
	@RequestMapping(value="/register/register", method=RequestMethod.POST)
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

	@RequestMapping(value="/modify")
	public void modify(Model model){
		User user = userService.findLoginId(loginUser().getLoginId());
		
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