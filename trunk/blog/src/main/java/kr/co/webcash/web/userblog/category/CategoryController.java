package kr.co.webcash.web.userblog.category;

import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.PostService;

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
	

	@RequestMapping("/{categoryId}")
	public String home(Model model, @PathVariable String blogId, @PathVariable String categoryId){
		
		model.addAttribute("blog", blogService.findById(blogId));
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("postList", postService.listByBlogIdAndCategoryId(blogId, categoryId));
		
		return "/userblog/category/home";
	}

}
