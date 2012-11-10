package kr.co.webcash.web.userblog.category;

import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{blogId}/category")
public class CategoryController {
	
	@Autowired private PostService postService;
	@Autowired private CategoryService categoryService;
	@Autowired private BlogService blogService;
	

	@RequestMapping("/{displayId}")
	public String home(Model model, @PathVariable String blogId, @PathVariable String displayId){
		model.addAttribute("postList", postService.listByBlogIdAndCategoryDisplayId(blogId, Long.valueOf(displayId)));
		
		return "/userblog/category/home";
	}

}
