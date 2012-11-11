package kr.co.webcash.domain;

import java.util.ArrayList;
import java.util.List;

import kr.co.webcash.domain.blog.Blog;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class FavoriteSameTest {
	
	@Test
	public void test(){
		
		Favorite favorite = new Favorite();
		favorite.setLoginId("kwakyc87");
		Blog blog = new Blog();
		blog.setId("1");
		favorite.setBlog(blog);
		
		List<Favorite> favoriteList = new ArrayList<Favorite>();
		favoriteList.add(favorite);
		
		Favorite newFavorite = new Favorite();
		newFavorite.setLoginId("kwakyc87");
		Blog newBlog = new Blog();
		newBlog.setId("1");
		newFavorite.setBlog(newBlog);
		
		favoriteList.remove(newFavorite);
		
		assertThat(favoriteList.size(), is(0));
	}

}
