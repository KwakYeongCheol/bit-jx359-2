package kr.co.webcash.web;

import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
public class LoginController {
	
	@Autowired private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(){
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String loginAction(@RequestParam String loginId, @RequestParam String password, Model model){
		
		if(userService.checkLoginIdAndPassword(loginId, password)){
			LoginUser loginUser = new LoginUser();
			loginUser.setLoginId(loginId);
			
			model.addAttribute("loginUser", loginUser);
			return "redirect:/";
		}
		
		return "redirect:/login";
	}
	
}
