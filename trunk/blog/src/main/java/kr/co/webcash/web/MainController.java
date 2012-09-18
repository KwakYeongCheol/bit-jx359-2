package kr.co.webcash.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public void main(){
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("loginUser");
		
		return "redirect:/";
	}

}
