package kr.co.webcash.web.userblog.admin.visit_history;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.blog.BlogVisitHistoryService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/visit_history")
public class AdminVisitHistoryController {
	@Autowired private CategoryService categoryService;
	@Autowired private BlogVisitHistoryService blogVisitHistoryService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping
	public String home(@PathVariable String blogId, 
			@RequestParam(defaultValue="1") int pageNumber,
			Model model){
		
		Page page = new Page(pageNumber, blogVisitHistoryService.countByBlogId(blogId));
		
		model.addAttribute("visitHistoryList", blogVisitHistoryService.listByBlogIdAndPage(blogId, page));
		model.addAttribute("page", page);
		
		return "/userblog/admin/visit_history/home";
	}
}
