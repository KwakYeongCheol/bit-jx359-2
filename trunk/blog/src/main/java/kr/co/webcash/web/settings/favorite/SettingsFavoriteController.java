package kr.co.webcash.web.settings.favorite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/favorite")
public class SettingsFavoriteController {
	@RequestMapping()
	public String favorite(){
		return "/settings/favorite/home";
	}
}
