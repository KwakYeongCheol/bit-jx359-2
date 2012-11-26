package kr.co.webcash.web.userblog.category;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/category")
public class CategoryController {
	
	@Autowired private PostService postService;
	@Autowired private CategoryService categoryService;
	@Autowired private BlogService blogService;
	
	@Inject private Provider<LoginUser> loginUserProvider;

	@RequestMapping("/{displayId}")
	public String home(@PathVariable String blogId, @PathVariable long displayId, 
			@RequestParam(defaultValue="1")	int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){
		Category findCategory = categoryService.findByBlogIdAndDisplayId(blogId, displayId);
		
		if(findCategory == null){
			model.addAttribute("message", "요청하신 카테고리가 존재하지 않습니다.");
			return "/jinbo/common/notfound";
		}
		
		LoginUser loginUser = loginUserProvider.get();
		
		if(loginUser.isLoggedIn() && blogService.isAdmin(blogId, loginUser.getLoginUser())){
			Page page = new Page(pageNumber, postService.countByCategory(findCategory));
			findCategory.setPostList(postService.listByBlogIdAndCategoryDisplayIdAndPage(blogId, displayId, page));
			model.addAttribute("pageCategory", findCategory);
			model.addAttribute("page", page);
		}else{
			Page page = new Page(pageNumber, postService.countPublicByCategory(findCategory));
			findCategory.setPostList(postService.listPublicByBlogIdAndCategoryDisplayIdAndPage(blogId, displayId, page));
			model.addAttribute("pageCategory", findCategory);
			model.addAttribute("page", page);
		}
		
		return "/jinbo/home";
	}
}
