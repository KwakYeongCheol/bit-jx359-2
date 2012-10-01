package kr.co.webcash.web.userblog.admin.post;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Post;
import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.PostService;
import kr.co.webcash.service.TrackbackService;
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
@SessionAttributes(value={"categoryList", "post"})
@RequestMapping("/{blogId}/admin/post")
public class PostController {
	
	@Autowired private PostService postService;
	@Autowired private BlogService blogService;
	@Autowired private CategoryService categoryService;
	@Autowired private TrackbackService trackbackService;
	
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@ModelAttribute("loginUser")
	public User loginUser(){
		return this.loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping()
	public String home(@PathVariable String blogId, Model model){
		model.addAttribute("postList", postService.listByBlogId(blogId));
		return "/userblog/admin/home";
	}
	
	@RequestMapping("/write")
	public String write(@PathVariable String blogId, Model model){
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("post", new Post());
		return "/userblog/admin/post/write";
	}
	
	@RequestMapping(value="/scrap")
	public String scrap(@ModelAttribute Post post, @PathVariable String blogId, Model model){
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		return "/userblog/admin/post/write";
	}
	
	@RequestMapping(value= "/writeAction", method=RequestMethod.POST)
	public String writeAction(@ModelAttribute Post post, 
			@RequestParam String trackbackURL, 
			Model model, HttpServletRequest request){
		
		Blog blog = blogService.findByUserLoginId(loginUser().getLoginId());
		post.setBlog(blog);
		post.setId(postService.findLastIdByBlogId(blog.getId()) + 1);
		post.setDateCreated(new Date(System.currentTimeMillis()));		
		postService.save(post);
	
		if(trackbackURL != null && trackbackURL.length() > 0){
			Trackback trackback = new Trackback();
			
			if(trackback != null){
				trackback.setUrl(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + post.getBlog().getId() + "/" + post.getId());
				trackback.setTitle(post.getTitle());
				trackback.setDateCreated(new Date(System.currentTimeMillis()));
				
				if(trackbackService.sendTrackback(trackbackURL, trackback)){
					/*
					 * Trackback Log 구현해야함
					 */
					System.out.println(trackbackURL);
//					Trackback myTrackback = new Trackback();
//					myTrackback.setBlogId(post.getBlog().getId());
//					myTrackback.setPostId(post.getId());
//					myTrackback.setUrl(trackbackURL);
//					myTrackback.setTitle("Trackback title");
//					myTrackback.setDateCreated(new Date(System.currentTimeMillis()));
//					
//					trackbackService.add(myTrackback);
				}
			}
		}
		
		return "redirect:/" + blog.getId() + "/admin";
	}
	
	
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long id, Model model){
		Post post = postService.findByIdAndBlogId(id,blogId);
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		
		return "/userblog/admin/post/modify";
	}
	
	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId, @ModelAttribute Post post){	
		Blog blog = new Blog();
		blog.setId(blogId);
		post.setBlog(blog);
		postService.update(post);
		
		return "redirect:/" + blogId + "/admin";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam long id, @PathVariable String blogId){
		Post post = new Post();
		post.setId(id);
		Blog blog = new Blog();
		blog.setId(blogId);
		post.setBlog(blog);
		
		postService.delete(post);
		
		return "redirect:/" + blogId + "/admin";
	}
}
