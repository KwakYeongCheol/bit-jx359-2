package kr.co.webcash.web;

import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
public class MainController {
	@Autowired BlogService blogService;
	
	@RequestMapping("/")
	public String main(HttpSession session, Model model){
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		if(loginUser != null){
			model.addAttribute("blog", blogService.findByUserLoginId(loginUser.getLoginId()));
		}
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("loginUser");
		
		return "redirect:/";
	}

}
