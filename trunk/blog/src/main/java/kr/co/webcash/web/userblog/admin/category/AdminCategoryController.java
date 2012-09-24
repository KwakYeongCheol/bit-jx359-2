package kr.co.webcash.web.userblog.admin.category;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping
	public String home(@PathVariable String blogId, Model model){
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		return "/userblog/admin/category/home";
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("category", new Category());
		return "/userblog/admin/category/add";
	}
	
	@RequestMapping(value="/addAction", method=RequestMethod.POST)
	public String addAciton(@ModelAttribute Category category, @PathVariable String blogId ){
		String redirectUrl = "redirect:/";
		if(loginUser() != null){
			Blog blog = blogService.findByUserLoginId(loginUser().getLoginId());
			
			category.setId(String.valueOf(categoryService.findLastIdByBlogId(blog.getId())+1));
			category.setBlog(blog);
			
			categoryService.save(category);
			redirectUrl = "redirect:/" + blog.getId() + "/admin/category";
		}
		return redirectUrl;
	}
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam String id, Model model){
		
		Category category = categoryService.findByIdAndBlogId(id,blogId);
		
		model.addAttribute("category", category);
		
		return "/userblog/admin/category/modify";
	}
	
	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, @ModelAttribute Category category){
		Blog blog = new Blog();
		blog.setId(blogId);
		category.setBlog(blog);
		
		categoryService.update(category);
		
		return "redirect:/" + blogId + "/admin/category";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String id, @PathVariable String blogId){		
		Category category = new Category();
		category.setId(id);
		Blog blog = new Blog();
		blog.setId(blogId);
		category.setBlog(blog);
		
		categoryService.delete(category);
		
		return "redirect:/" + blogId + "/admin/category/";
	}
}
