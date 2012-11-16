package kr.co.webcash.web.userblog.admin.guestbook;

import java.util.List;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Page;
import kr.co.webcash.service.GuestbookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{blogId}/admin/guestbook")
public class UserBlogAdminGuestbookController {
	
	@Autowired private GuestbookService guestbookService;
	
	@RequestMapping
	public String home(@PathVariable String blogId, @RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize, Model model){
		Page page = new Page(pageNumber, guestbookService.countByBlogId(blogId), pageSize);
		List<Guestbook> listByBlogIdAndPage = guestbookService.listByBlogIdAndPage(blogId, page);
		
		model.addAttribute("guestbookList", listByBlogIdAndPage);
		model.addAttribute("page", page);
		
		return "/userblog/admin/guestbook/home";
	}

}
