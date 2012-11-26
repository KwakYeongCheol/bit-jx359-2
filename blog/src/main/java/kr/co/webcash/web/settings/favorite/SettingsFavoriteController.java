package kr.co.webcash.web.settings.favorite;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.user.User;
import kr.co.webcash.service.FavoriteService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/settings/favorite")
public class SettingsFavoriteController {
	
	@Autowired private FavoriteService favoriteService;
	@Inject private Provider<LoginUser> loginUserProvider;
	
	private User loginUser(){
		return loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping()
	public String favorite(){
		return "/settings/favorite/home";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String blogId, @RequestParam(required=false) String redirectURI){
		Blog blog = new Blog();
		blog.setId(blogId);
		
		Favorite favorite = new Favorite();
		favorite.setLoginId(loginUser().getLoginId());
		favorite.setBlog(blog);
		
		favoriteService.delete(favorite);
		this.loginUserProvider.get().removeFavorite(favorite);
		
		if(redirectURI != null)		return "redirect:" + redirectURI;
		
		return "redirect:/settings/favorite";
	}
}
