package kr.co.webcash.web.user;

import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user", "loginUser"})
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;
	
	@RequestMapping("/register/step01")
	public void step01(Model model){
		model.addAttribute("user", new User());
	}
	
	@RequestMapping(value="/register/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user, Model model, HttpSession session){
		if(userService.save(user)){
			LoginUser loginUser = new LoginUser();
			loginUser.setLoginId(user.getLoginId());
			model.addAttribute("loginUser", loginUser);
			
			session.removeAttribute("user");
			
			return "redirect:/blog/settings";
		}
		
		return "redirect:/user/register/step01";
	}

}
