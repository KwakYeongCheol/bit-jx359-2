package kr.co.webcash.web.userblog.admin.visit_history;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Provider;

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
	public String home(@PathVariable String blogId, Model model){
		ArrayList visit = blogVisitHistoryService.test(blogId);
		model.addAttribute("visit", visit);
		model.addAttribute("day", visit.size());
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		return "/userblog/admin/visit_history/home";
	}
}
