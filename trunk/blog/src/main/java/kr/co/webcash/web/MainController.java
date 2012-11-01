package kr.co.webcash.web;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired BlogService blogService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping("/")
	public String main(){
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		this.loginUserProvider.get().logout();
		
		return "redirect:/";
	}

}
