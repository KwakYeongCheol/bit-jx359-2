package kr.co.webcash.web.userblog.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{blogId}/admin")
public class UserBlogAdminController {
	
	@RequestMapping
	public String main(){
		return "/userblog/admin/home";
	}
	
	@RequestMapping("/post/write")
	public String write(){
		return "/userblog/admin/post/write";
	}
	
	

}
