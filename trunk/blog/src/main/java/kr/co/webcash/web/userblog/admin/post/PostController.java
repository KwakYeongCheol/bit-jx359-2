package kr.co.webcash.web.userblog.admin.post;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Scrap;
import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.User;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.service.BlogService;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.PostService;
import kr.co.webcash.service.TrackbackService;
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
	public String home(@PathVariable String blogId, Model model) {
		model.addAttribute("postList", postService.listAll(blogId));
		return "/userblog/admin/home";
	}

	@RequestMapping("/write")
	public String write(@PathVariable String blogId, Model model) {
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("post", new Post());
		return "/userblog/admin/post/write";
	}

	@RequestMapping(value = "/scrap")
	public String scrap(@ModelAttribute Scrap scrap, @PathVariable String blogId, Model model) {
		model.addAttribute("scrap", scrap);
		
		return this.write(blogId, model);
	}

	@RequestMapping(value = "/writeAction", method = RequestMethod.POST)
	public String writeAction(@ModelAttribute Post post,
			@RequestParam String trackbackURL, Model model,
			@PathVariable String blogId,
			HttpServletRequest request, BindingResult result) {
		this.postValidator.validate(post, result);
		if (!result.hasErrors()) {
			post.setCategory(categoryService.findByBlogIdAndDisplayId(blogId, post.getCategory().getDisplayId()));
			post.setDisplayId(postService.findLastDisplayIdByBlogId(blogId) + 1);
			post.setDateCreated(new Date(System.currentTimeMillis()));
			
			postService.save(post);

			if (trackbackURL != null && trackbackURL.length() > 0) {
				Trackback trackback = new Trackback();

				if (trackback != null) {
					trackback.setUrl(request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + "/"	+ blogId + "/" + post.getDisplayId());
					trackback.setTitle(post.getTitle());
					trackback.setDateCreated(new Date(System.currentTimeMillis()));

					if (trackbackService.sendTrackback(trackbackURL, trackback)) {
						/*
						 * TODO  Trackback Log 구현해야함
						 */
						// Trackback myTrackback = new Trackback();
						// myTrackback.setBlogId(post.getBlog().getId());
						// myTrackback.setPostId(post.getId());
						// myTrackback.setUrl(trackbackURL);
						// myTrackback.setTitle("Trackback title");
						// myTrackback.setDateCreated(new
						// Date(System.currentTimeMillis()));
						//
						// trackbackService.add(myTrackback);
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

		return "/userblog/admin/post/modify";
	}

	@RequestMapping(value = "/modifyAction", method = RequestMethod.POST)
	public String modifyAction(@PathVariable String blogId,	@ModelAttribute Post post, BindingResult result) {
		this.postValidator.validate(post, result);
		if (!result.hasErrors()) {
			Category category = categoryService.findByBlogIdAndDisplayId(blogId, post.getCategory().getDisplayId());
			post.setCategory(category);
			postService.update(post);
			return "redirect:/" + blogId + "/admin";
		}
		return "/userblog/admin/post/modify";
	}

	@RequestMapping("/delete")
	public String delete(@PathVariable String blogId, @RequestParam long displayId) {
		Post post = postService.findByBlogIdAndDisplayId(blogId, displayId);
		
		postService.delete(post);

		return "redirect:/" + blogId + "/admin";
	}
}
