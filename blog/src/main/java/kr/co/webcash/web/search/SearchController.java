package kr.co.webcash.web.search;

import kr.co.webcash.domain.Page;
import kr.co.webcash.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired private SearchService searchService;
	
	@RequestMapping()
	public void search(@RequestParam String query, 
			@RequestParam(defaultValue="1")	int pageNumber, @RequestParam(defaultValue="10") int pageSize,
			Model model){
		
		Page page = new Page(pageNumber, searchService.countByQuery(query), pageSize);
		
		model.addAttribute("query", query);
		model.addAttribute("search", searchService.findAllByQueryAndPage(query, page));
		model.addAttribute("page", page);
	}

}
