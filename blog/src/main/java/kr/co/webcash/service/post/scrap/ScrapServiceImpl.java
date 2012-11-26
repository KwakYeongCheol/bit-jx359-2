package kr.co.webcash.service.post.scrap;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.scrap.Scrap;
import kr.co.webcash.domain.post.scrap.ScrapTarget;
import kr.co.webcash.repository.ScrapRepository;
import kr.co.webcash.service.notification.NotificationService;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapServiceImpl implements ScrapService{
	
	@Autowired private PostService postService;
	@Autowired private NotificationService notificationService;
	
	@Autowired private ScrapRepository scrapRepository;

	@Override
	public List<Scrap> listByTargetBlog(Blog blog) {
		return scrapRepository.findAllByTargetBlogId(blog.getId());
	}

	@Override
	public void save(Post post) {
		List<Scrap> scrapList = Scrap.convert(post.getContents());
		
		for(Scrap scrap : scrapList){
			Post targetPost = postService.findByBlogIdAndDisplayId(scrap.getTargetBlogId(), scrap.getTargetPostDisplayId());
			if(targetPost == null)	continue;
			
			if(targetPost.getPostMetadata().getCanScrap()){
				scrap.setPost(post);
				scrap.setTarget(new ScrapTarget(targetPost, targetPost.getCurrentRevision().getDisplayId()));
				
				save(scrap);
			}
		}
	}
	
	@Override
	public void save(Scrap scrap) {
		scrapRepository.insert(scrap);
	}
	
	@Override
	public void sendNotification(Post post) {
		List<Scrap> scrapList = scrapRepository.findAllByTargetPostId(post.getId());
		
		for(Scrap scrap : scrapList){
			notificationService.sendNotification(scrap, false);
		}
	}


	@Override
	public void convertFromScrapTagToScrapContents(Post post) {
		StringBuffer buffer = new StringBuffer(post.getContents());
		List<Scrap> scrapList = Scrap.convert(post.getContents());
		
		for(Scrap scrap : scrapList){
			Post findPost = postService.findByBlogIdAndDisplayId(scrap.getTargetBlogId(), scrap.getTargetPostDisplayId());
			Scrap findScrap = scrapRepository.findByPostIdAndTargetPostIdAndTargetPostRevisionId(
					post.getId(),
					findPost.getId(),
					scrap.getTargetPostRevisionId());
			
			if(findScrap == null)		continue;
			
			StringBuffer changeText = new StringBuffer();
			changeText.append("<scrap>")
					.append("<div class=\"ref\">")
						.append("출처 : ")
						.append("<a href=\"").append("/" + findPost.getBlogId()).append("\">")
							.append(findPost.getBlogTitle())
						.append("</a> > ")
						.append("<a href=\"").append(findPost.getURI()).append("\">")
							.append(findPost.getTitle())
						.append("</a>")
					.append("</div>")
					.append("<div class=\"contents\">")
						.append(findPost.getContents(scrap.getTargetPostRevisionId()))
					.append("</div>")
				.append("</scrap>");
			
			int startIndex = buffer.indexOf(findScrap.getTag());
			
			if(startIndex != -1){
				buffer.replace(startIndex, startIndex + findScrap.getTag().length(), changeText.toString());
			}
		}
		
		post.setContents(buffer.toString());
	}
}
