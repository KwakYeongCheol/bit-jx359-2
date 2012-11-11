package kr.co.webcash.web.userblog.admin.visit_history;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.AdminCategoryValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/{blogId}/admin/visit_history")
public class AdminVisitHistoryController {
	@Autowired private BlogService blogService;
	@Autowired private CategoryService categoryService;
	
	@Autowired private AdminCategoryValidator categoryValidator;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping
	public String home(@PathVariable String blogId, Model model){
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		return "/userblog/admin/visit_history/home";
	}
}
