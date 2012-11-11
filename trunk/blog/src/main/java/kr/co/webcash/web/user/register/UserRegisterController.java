package kr.co.webcash.web.user.register;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.UserService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.UserRegisterValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
@RequestMapping("/user/register")
public class UserRegisterController {
	
	@Autowired private UserService userService;
	@Autowired private UserRegisterValidator validator;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping("/checkLoginId/{loginId}")
	@ResponseBody
	public Map checkLoginId(@PathVariable String loginId){
		Map result = new HashMap();
		
		String duplicated = "duplicated";
		
		if(!(loginId == null && loginId.length() == 0)){
			if(userService.findByLoginId(loginId) == null){
				result.put(duplicated, false);
				return result;
			}
		}
		
		result.put(duplicated, true);
		return result;
	}
	
	@RequestMapping("/step01")
	public String step01(){
		return "redirect:/user/register/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void showRegisterForm(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user == null)	model.addAttribute("user", new User());	
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user, @RequestParam String passwordConfirm,
			@RequestParam int year, @RequestParam int month, @RequestParam int day,
			BindingResult result, Model model, SessionStatus status){
		this.validator.validate(user, result);
		
		if(result.hasErrors()){
			return "/user/register/register";
		}
		
		if(!user.getPassword().equals(passwordConfirm)){
			result.rejectValue("password", "field.confirm.user.password");
			return "/user/register/register";
		}
		
		String birth = year + "-" + month + "-" + day;
		try {
			Date birthday = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).parse(birth);
			user.setBirthday(birthday);
			
			if(userService.save(user)){
				status.setComplete();
				this.loginUserProvider.get().login(user);
				
				return "redirect:/user/register/complete";
			}
		} catch (ParseException e) {
		}
		
		return "/user/register/register";
	}
	
	@RequestMapping("/complete")
	public void complete(){
	}
}










