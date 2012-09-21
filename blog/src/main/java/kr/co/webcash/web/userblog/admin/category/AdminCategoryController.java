package kr.co.webcash.web.userblog.admin.category;

import javax.servlet.http.HttpSession;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.LoginUser;
import kr.co.webcash.domain.Post;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/category")
public class AdminCategoryController {
	@Autowired private BlogService blogService;
	@Autowired private CategoryService categoryService;
	
	@RequestMapping
	public String home(@PathVariable String blogId, Model model){
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));	
		return "/userblog/admin/category/home";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "/userblog/admin/category/add";
	}
	@RequestMapping(value="/addAction", method=RequestMethod.POST)
	public String addAciton(@RequestParam String title, HttpSession session, @PathVariable String blogId ){
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		String redirectUrl = "redirect:/";
		if(loginUser != null){
			Blog blog = blogService.findByUserLoginId(loginUser.getLoginId());
			
			Category category = new Category();
			category.setId(String.valueOf(categoryService.findLastIdByBlogId(blog.getId())+1));
			category.setTitle(title);
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
	public String modifyAction(@PathVariable String blogId, @RequestParam String title, @RequestParam String id){
		Category category = new Category();
		Blog blog = new Blog();
		blog.setId(blogId);
		category.setId(id);
		category.setBlog(blog);
		category.setTitle(title);
		
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
