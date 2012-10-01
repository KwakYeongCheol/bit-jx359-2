package kr.co.webcash.web.userblog.trackback;

import kr.co.webcash.domain.Trackback;
import kr.co.webcash.service.TrackbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trackback/{blogId}")
public class TrackbackController {
	
	@Autowired private TrackbackService trackbackService;
	
	@RequestMapping(value = "/{postId}", method=RequestMethod.POST)
	@ResponseBody
	public String trackback(@PathVariable String blogId, @PathVariable long postId, 
			@RequestParam String url, @RequestParam("blog_name") String blogName,
			@RequestParam String title, @RequestParam String excerpt){
		
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		builder.append("<response>");
		if(trackbackService.canTrackback(blogId, postId)){
			builder.append("<error>0</error>");
			trackbackService.add(new Trackback(blogId, postId, url, blogName, title, excerpt));
		}else{
			builder.append("<error>1</error>");
			builder.append("<message>What's up?</message>");
		}
		
		builder.append("</response>");
		
		return builder.toString();
	}
}
	