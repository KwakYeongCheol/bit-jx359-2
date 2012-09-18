package kr.co.webcash.web.user;

import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;
	
	@RequestMapping("/register/step01")
	public void step01(){
		
	}
	@RequestMapping(value="/register/register", method=RequestMethod.POST)
	public String register(@RequestParam String loginId, @RequestParam String password, @RequestParam String name){
		User user = new User();
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setName(name);
		
		userService.save(user);
		
		return "redirect:/";
	}

}
