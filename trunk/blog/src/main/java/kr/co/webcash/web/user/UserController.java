package kr.co.webcash.web.user;

import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.UserService;
import kr.co.webcash.web.validator.UserRegisterValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user", "loginUser"})
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private UserRegisterValidator validator;
	
	@RequestMapping("/home")
	public void Main(HttpSession session, Model model){
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		User user = userService.findLoginId(loginUser.getLoginId());
		System.out.println(user);
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
	public String register(@ModelAttribute User user, BindingResult result, Model model, HttpSession session){
		this.validator.validate(user, result);
		
		if(!result.hasErrors()){
			if(userService.save(user)){
				LoginUser loginUser = new LoginUser();
				loginUser.setLoginId(user.getLoginId());
				model.addAttribute("loginUser", loginUser);
				
				session.removeAttribute("user");
				
				return "redirect:/blog/settings";
			}
		}
		
		return "/user/register/register";
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public void modify(@ModelAttribute User user, Model model, HttpSession session){
		
	}
}
