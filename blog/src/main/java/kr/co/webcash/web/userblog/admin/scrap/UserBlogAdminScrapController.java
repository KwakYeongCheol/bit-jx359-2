package kr.co.webcash.web.userblog.admin.scrap;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.service.ScrapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{blogId}/admin/scrap")
public class UserBlogAdminScrapController {
	
	@Autowired private ScrapService scrapService;
	
	@RequestMapping
	public String home(@PathVariable String blogId, Model model){
		model.addAttribute("scrapList", scrapService.listAllByScrappedBlog(new Blog(blogId)));
		
		return "/userblog/admin/scrap/home";
	}
}
