package kr.co.webcash.web.userblog.admin.post;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.Trackback;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.scrap.Scrap;
import kr.co.webcash.domain.post.scrap.ScrapTarget;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.CategoryService;
import kr.co.webcash.service.TrackbackService;
import kr.co.webcash.service.blog.BlogService;
import kr.co.webcash.service.post.PostRevisionService;
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
@SessionAttributes(value = { "categoryList", "post", "scrap" })
@RequestMapping("/{blogId}/admin/post")
public class UserBlogAdminPostController {

	@Autowired	private PostService postService;
	@Autowired 	private BlogService blogService;
	@Autowired	private CategoryService categoryService;
	@Autowired	private TrackbackService trackbackService;
	@Autowired	private PostValidator postValidator;
	@Autowired private PostRevisionService postRevisionService;

	@Inject	private Provider<LoginUser> loginUserProvider;

	@ModelAttribute("loginUser")
	public User loginUser() {
		return this.loginUserProvider.get().getLoginUser();
	}

	@RequestMapping()
	public String home(@PathVariable String blogId, @RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize, Model model) {
		Page page = postService.getPage(blogId, pageNumber);
		
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("postList", postService.listByBlogIdAndPage(blogId, page));
		model.addAttribute("page", page);
		
		return "/userblog/admin/post/home";
	}
	
	@RequestMapping("/revision")
	public String revisionHome(@PathVariable String blogId, @RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize, Model model) {
		Page page = postService.getPage(blogId, pageNumber);
		
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("postList", postService.listByBlogIdAndPage(blogId, page));
		model.addAttribute("page", page);
		
		return "/userblog/admin/post/revision/home";
	}
	
	@RequestMapping("/revision/{postDisplayId}")
	public String revisionView(@PathVariable String blogId, @PathVariable long postDisplayId, Model model){
		
		Post findPost = postService.findByBlogIdAndDisplayId(blogId, postDisplayId);
		
		if(findPost == null)	return "redirect:/" + blogId + "/admin/post/revision";
		
		model.addAttribute("post", findPost);
		model.addAttribute("postRevisionList", postRevisionService.list(findPost));
		
		return "/userblog/admin/post/revision/view";
	}
	
	
	
	@RequestMapping("/category/{categoryDisplayId}")
	public String homeCategoryFilter(@PathVariable String blogId, @PathVariable long categoryDisplayId, 
			@RequestParam(defaultValue="1") int pageNumber, @RequestParam(defaultValue="10") int pageSize, Model model){
		
		Category findCategory = categoryService.findByBlogIdAndDisplayId(blogId, categoryDisplayId);
		
		Page page = new Page(pageNumber, postService.countByCategory(findCategory));
		
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("postList", postService.listByBlogIdAndCategoryDisplayId(blogId, categoryDisplayId));
		model.addAttribute("page", page);
		
		return "/userblog/admin/post/home";
	}
	
	@RequestMapping(value = "/scrap")
	public String scrap(@PathVariable String blogId, 
			@RequestParam String targetBlogId, @RequestParam long targetPostDisplayId, @RequestParam long targetPostRevisionId,
			Model model) {
		Scrap scrap = new Scrap();
		
		Post targetPost = postService.findByBlogIdAndDisplayId(targetBlogId, targetPostDisplayId);
		
		if(blogService.isAdmin(targetPost.getBlogId(), loginUser())){
			model.addAttribute("message", "내 글을 스크랩할 수 없습니다.");
			return "/common/back";
		}
		
		scrap.setTarget(new ScrapTarget(targetPost, targetPostRevisionId));
		
		model.addAttribute("scrap", scrap);
		model.addAttribute("postList", postService.listByBlogId(blogId));
		
		return "/userblog/admin/post/scrap/complete";
	}
	
	@RequestMapping("/addScrap")
	public String scrapToPostWrite(@PathVariable String blogId, @ModelAttribute Scrap scrap,
			@RequestParam long postDisplayId,
			Model model){
		Post post;
		if(postDisplayId == 0){
			post = new Post();
			post.setContents(scrap.getTag());
		}else{
			post = postService.findByBlogIdAndDisplayIdWithOutWrap(blogId, postDisplayId);
			if(post == null)		return "/userblog/admin/post/scrap/complete";
			
			post.setContents(post.getContents() + "<br />" + scrap.getTag());
		}
		
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("post", post);
		
		return "/userblog/admin/post/editor";
	}
	
	@RequestMapping(value = "/updateScrap", method = RequestMethod.POST)
	public String updateScrap(@PathVariable String blogId, @RequestParam(required=false) String redirectURI, Model model,
			@RequestParam long postDisplayId, @RequestParam String targetBlogId, @RequestParam long targetPostDisplayId, @RequestParam long targetRevisionId 
			){
		
		Post findPost = postService.findByBlogIdAndDisplayId(blogId, postDisplayId);
		
		if(findPost != null){
			postService.updateScrap(findPost, targetBlogId, targetPostDisplayId, targetRevisionId);
		}
		
		if(redirectURI != null)	return "redirect:/redirectURI";
		else					return "redirect:/" + blogId + "/admin/external";
		
	}
	

	@RequestMapping("/write")
	public String write(@PathVariable String blogId, Model model) {
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("tempPostList", postService.tempListByBlogId(blogId));
		model.addAttribute("post", new Post());
		return "/userblog/admin/post/editor";
	}
	
	@RequestMapping("/editor")
	public String editor(@PathVariable String blogId, Model model) {
		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("tempPostList", postService.tempListByBlogId(blogId));
		model.addAttribute("post", new Post());
		return "/userblog/admin/post/editor";
	}
	
	@RequestMapping("/modify")
	public String modify(@PathVariable String blogId, @RequestParam long displayId, Model model) {
		Post post = postService.findByBlogIdAndDisplayIdWithOutWrap(blogId, displayId);

		model.addAttribute("categoryList", categoryService.listByBlogId(blogId));
		model.addAttribute("tempPostList", postService.tempListByBlogId(blogId));
		model.addAttribute("post", post);

		return "/userblog/admin/post/editor";
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
					
					Blog findBlog = blogService.findById(blogId);
					
					trackback.setUrl(urlBuilder.toString());
					trackback.setBlogName(findBlog.getTitle());
					trackback.setTitle(post.getTitle());
					trackback.setExcerpt("trackback 남깁니다 :D");
					
					trackback.setDateCreated(new Date(System.currentTimeMillis()));

					if (trackbackService.sendTrackback(trackbackURL, trackback)) {
						/*
						 * TODO  Trackback Log 구현해야함
						 */
					}
					
				}
			}

			return "redirect:/" + blogId + "/" + post.getDisplayId();
		}
		return "/userblog/admin/post/editor";
	}

	@RequestMapping("/delete")
	public String delete(@PathVariable String blogId, @RequestParam long displayId, @RequestParam(required=false) String redirectURI) {
		Post post = postService.findByBlogIdAndDisplayId(blogId, displayId);
		postService.delete(post);
		
		if(redirectURI != null){
			return "redirect:" + redirectURI;
		}

		return "redirect:/" + blogId + "/admin";
	}
}
