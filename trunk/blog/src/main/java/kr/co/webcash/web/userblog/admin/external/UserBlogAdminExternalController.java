package kr.co.webcash.web.userblog.admin.external;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.service.post.scrap.ScrapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/external")
public class UserBlogAdminExternalController {
	
	@Autowired private ScrapService scrapService;
	
	@RequestMapping()
	public String home(@PathVariable String blogId, 
			@RequestParam(defaultValue="1")	int pageNumber,
			Model model){
		
		Page page = new Page(pageNumber, scrapService.countByBlog(new Blog(blogId)));
		model.addAttribute("scrapList", scrapService.listByBlogAndPage(new Blog(blogId), page));
		model.addAttribute("page", page);
		
		return "/userblog/admin/external/home";
	}
}
