package kr.co.webcash.web.recent;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.TagCategory;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recent/{categoryTitle}")
public class RecentController {
	
	@Autowired private PostService postService;
	
	@RequestMapping()
	public String home(
			@PathVariable String categoryTitle,
			@RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){

		TagCategory tagCategory;
		
		try{
			tagCategory = TagCategory.valueOf(categoryTitle);
		}catch(IllegalArgumentException e){
			System.out.println("Invalid tag category title.");
			return "redirect:/";
		}
		
		Page page = new Page(pageNumber, postService.countByTagCategory(tagCategory));
		
		model.addAttribute("postList", postService.listByTagCategoryAndPage(tagCategory, page));
		model.addAttribute("page", page);
		
		return "home";
	}
}
