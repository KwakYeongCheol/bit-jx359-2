package kr.co.webcash.web;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Page;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Inject private Provider<LoginUser> loginUserProvider;

	@Autowired BlogService blogService;
	@Autowired private PostService postService;
	
	
	@RequestMapping("/")
	public String main(@RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){
		
		Page page = new Page(pageNumber, postService.countPublic(), pageSize);
		model.addAttribute("postList", postService.listPublicByPage(page));
		model.addAttribute("page", page);
		
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(@RequestParam(required=false) String redirectURI){
		this.loginUserProvider.get().logout();
		
		if(redirectURI != null){
			return "redirect:" + redirectURI;
		}
		
		return "redirect:/";
	}

}
