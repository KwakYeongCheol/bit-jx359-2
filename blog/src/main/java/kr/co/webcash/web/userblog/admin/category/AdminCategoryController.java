package kr.co.webcash.web.userblog.admin.category;

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
@SessionAttributes("category")
@RequestMapping("/{blogId}/admin/category")
public class AdminCategoryController {
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
		model.addAttribute("category", new Category());
		return "/userblog/admin/category/home";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addAciton(@ModelAttribute Category category,  BindingResult result , @PathVariable String blogId){
		String redirectUrl = "redirect:/";
		
		this.categoryValidator.validate(category, result);
		if(!result.hasErrors()){
			if(loginUser() != null){
				category.setBlog(new Blog(blogId));
				categoryService.save(category);
				
				redirectUrl = "redirect:/" + blogId + "/admin/category";
				return redirectUrl;
			}
		}
		return "/userblog/admin/category/home";
	}
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long displayId, Model model){
		Category category = categoryService.findByBlogIdAndDisplayId(blogId, displayId);
		
		model.addAttribute("category", category);
		
		return "/userblog/admin/category/modify";
	}
	
	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, @ModelAttribute Category category, BindingResult result){
		this.categoryValidator.validate(category, result);
		
		if(!result.hasErrors()){
			Category findCategory = categoryService.findByBlogIdAndDisplayId(blogId, category.getDisplayId());
			
			Blog blog = new Blog();
			blog.setId(blogId);
			category.setBlog(blog);
			category.setId(findCategory.getId());
			
			categoryService.update(category);
			return "redirect:/" + blogId + "/admin/category";
		}
		return "/userblog/admin/category/modify";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam long displayId, @PathVariable String blogId){		
		Category category = categoryService.findByBlogIdAndDisplayId(blogId, displayId);
		
		categoryService.delete(category);
		
		return "redirect:/" + blogId + "/admin/category/";
	}
}
