package kr.co.webcash.web.userblog.admin.post;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.scrap.Scrap;
import kr.co.webcash.domain.post.scrap.ScrapTarget;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.TrackbackService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostService;
import kr.co.webcash.web.security.LoginUser;
import kr.co.webcash.web.validator.PostValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@SessionAttributes(value = { "categoryList", "post" })
@RequestMapping("/{blogId}/admin/post")
public class PostController {

	@Autowired	private PostService postService;
	@Autowired 	private BlogService blogService;
	@Autowired	private CategoryService categoryService;
	@Autowired	private TrackbackService trackbackService;
	@Autowired	private PostValidator postValidator;

	@Inject	private Provider<LoginUser> loginUserProvider;

	@ModelAttribute("loginUser")
	public User loginUser() {
		return this.loginUserProvider.get().getLoginUser();
	}

	@RequestMapping()
	public String home(@PathVariable String blogId, @RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize, Model model) {
		Page page = postService.getPage(blogId, pageNumber);
		
		model.addAttribute("postList", postService.listByBlogIdAndPage(blogId, page));
		model.addAttribute("page", page);
		
		return "/userblog/admin/post/home";
	}

	@RequestMapping("/write")
	public String write(@PathVariable String blogId, Model model) {
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("post", new Post());
		return "/userblog/admin/post/write";
	}

	@RequestMapping(value = "/scrap")
	public String scrap(@PathVariable String blogId, 
			@RequestParam String targetBlogId, @RequestParam long targetPostDisplayId, @RequestParam long targetPostRevisionId,
			Model model) {
		
		Scrap scrap = new Scrap();
//		scrap.setTargetBlog(new Blog(targetBlogId));
//		scrap.setTargetPostDisplayId(targetPostDisplayId);
//		scrap.setTargetPostRevisionId(targetPostRevisionId);
		
		Post targetPost = postService.findByBlogIdAndDisplayId(targetBlogId, targetPostDisplayId);
		scrap.setTarget(new ScrapTarget(targetPost, targetPostRevisionId));
		
		Post post = new Post();
		post.setContents(scrap.getTag());
		
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("post", post);
		
		return "/userblog/admin/post/write";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String writeAction(@PathVariable String blogId, 
			@ModelAttribute Post post, @RequestParam(required=false) String trackbackURL, Model model,			
			HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder, BindingResult result) {
		this.postValidator.validate(post, result);
		if (!result.hasErrors()) {
			
			if(post.getDisplayId() == 0){
				post.setDisplayId(postService.findLastDisplayIdByBlogId(blogId) + 1);
			}
			
			post.setCategory(categoryService.findByBlogIdAndDisplayId(blogId, post.getCategory().getDisplayId()));
			post.setDateCreated(new Date(System.currentTimeMillis()));
			
			postService.save(post);

			if (trackbackURL != null && trackbackURL.length() > 0) {
				Trackback trackback = new Trackback();

				if (trackback != null) {
					UriComponents components = uriComponentsBuilder.build();
					
					StringBuilder urlBuilder = new StringBuilder();
					urlBuilder.append(components.toUriString()).append("/").append(blogId).append("/").append(post.getDisplayId());
					
					trackback.setUrl(urlBuilder.toString());
					trackback.setTitle(post.getTitle());
					trackback.setDateCreated(new Date(System.currentTimeMillis()));

					if (trackbackService.sendTrackback(trackbackURL, trackback)) {
						/*
						 * TODO  Trackback Log 구현해야함
						 */
					}
				}
			}

			return "redirect:/" + blogId + "/admin";
		}
		return "/userblog/admin/post/write";
	}

	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long displayId, Model model) {
		Post post = postService.findByBlogIdAndDisplayId(blogId, displayId);

		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));

		return "/userblog/admin/post/write";
	}

//	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
//	public String modifyAction(@PathVariable String blogId,	@ModelAttribute Post post, BindingResult result) {
//		this.postValidator.validate(post, result);
//		if (!result.hasErrors()) {
//			Category category = categoryService.findByBlogIdAndDisplayId(blogId, post.getCategory().getDisplayId());
//			post.setCategory(category);
//			postService.update(post);
//			return "redirect:/" + blogId + "/admin";
//		}
//		return "/userblog/admin/post/modify";
//	}

	@RequestMapping("/delete")
	public String delete(@PathVariable String blogId, @RequestParam long displayId) {
		Post post = postService.findByBlogIdAndDisplayId(blogId, displayId);
		
		postService.delete(post);

		return "redirect:/" + blogId + "/admin";
	}
}
