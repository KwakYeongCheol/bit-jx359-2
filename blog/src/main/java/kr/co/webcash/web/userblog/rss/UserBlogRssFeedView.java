package kr.co.webcash.web.userblog.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.webcash.domain.post.Post;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;


public class UserBlogRssFeedView extends AbstractRssFeedView{
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
		super.buildFeedMetadata(model, feed, request);
		
		feed.setTitle("JINBO");
		feed.setDescription("Welcome to our JINBO service. JINBO means JINBO IS NOT BLOG");
		feed.setLink("http://localhost:8080");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Post> postList = (List<Post>) model.get("postList");
		List<Item> itemList = new ArrayList<Item>();
		
		for(Post post : postList){
			Item item = new Item();
			
			item.setTitle(post.getTitle());
			item.setUri("http://localhost:8080/" + model.get("blogId") + "/" + post.getDisplayId());
			item.setLink("http://localhost:8080/" + model.get("blogId") + "/" + post.getDisplayId());
			
			itemList.add(item);
		}
		
		return itemList;
	}
}
