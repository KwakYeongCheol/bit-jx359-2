package kr.co.webcash.web.userblog.search;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/search")
public class UserBlogSearchController {
	
	@Autowired private PostService postService;
	
	@RequestMapping()
	public String search(@PathVariable String blogId, @RequestParam String query,
			@RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){
		
		Page page = new Page(pageNumber, postService.countByBlogIdAndQuery(blogId, query));
		Category pageCategory = new Category(query);
		pageCategory.setPostList(postService.searchByBlogId(blogId, query, page));
		
		model.addAttribute("query", query);
		model.addAttribute("pageCategory", pageCategory);
		model.addAttribute("page", page);
		
		return "/jinbo/search/home";
	}

}
