package kr.co.webcash.web.favorite;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Favorite;
import kr.co.webcash.domain.User;
import kr.co.webcash.service.FavoriteService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
	
	@Autowired private FavoriteService favoriteService;
	@Inject private Provider<LoginUser> loginUserProvider;
	
	private User loginUser() {
		return loginUserProvider.get().getLoginUser();
	}
	
	@RequestMapping
	public String Main(){
		return "favorite/home";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String blogId){
		Blog blog = new Blog();
		blog.setId(blogId);
		Favorite favorite = new Favorite();
		favorite.setLoginId(loginUser().getLoginId());
		favorite.setBlog(blog);
		
		favoriteService.delete(favorite);
		this.loginUserProvider.get().removeFavorite(favorite);
		
		return "redirect:/favorite";
	}
}