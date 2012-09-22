package kr.co.webcash.web.favorite;

import javax.inject.Inject;
import javax.inject.Provider;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Favorite;
import kr.co.webcash.service.FavoriteService;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
	
	@Autowired private FavoriteService favoriteService;
	@Inject private Provider<LoginUser> loginUserProvider;
	
	@RequestMapping
	public String Main(Model model){
		model.addAttribute("favoritesList", favoriteService.listByLoginId(loginUserProvider.get().loginUser().getLoginId()));
		return "favorite/home";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam String id){
		Blog blog = new Blog();
		blog.setId(id);
		Favorite favorite = new Favorite();
		favorite.setFavoriteBlog(blog);
		favoriteService.delete(favorite);
		return "redirect:/favorite";
	}
}
