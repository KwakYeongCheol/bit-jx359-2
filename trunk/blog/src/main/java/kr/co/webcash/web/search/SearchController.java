package kr.co.webcash.web.search;

import kr.co.webcash.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired private SearchService searchService;
	
	@RequestMapping
	public void searchForm(){
		
	}

	@RequestMapping(method=RequestMethod.POST)
	public void search(@RequestParam String query, Model model){
		model.addAttribute("query", query);
		model.addAttribute("search", searchService.findAllByQuery(query));
	}

}
