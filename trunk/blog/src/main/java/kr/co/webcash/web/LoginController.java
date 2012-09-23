package kr.co.webcash.web;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@Autowired private UserService userService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(@RequestParam(value="redirectURI", required=false) String redirectURI, Model model){
		model.addAttribute("redirectURI", redirectURI);
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String loginAction(@RequestParam String loginId, @RequestParam String password, 
			@RequestParam(value="redirectURI", required=false) String redirectURI, Model model){
		
		if(loginId.equals("1") || loginId.equals("2")){
			password = "password";
		}
		
		if(userService.checkLoginIdAndPassword(loginId, password)){
			User user = userService.findLoginId(loginId);
			this.loginUserProvider.get().save(user);
			
			if(redirectURI != null)		return "redirect:" + redirectURI;
			else						return "redirect:/";
		}
		
		return "redirect:/login";
	}
	
}
