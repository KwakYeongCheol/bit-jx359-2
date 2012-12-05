package kr.co.webcash.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostTag;
import kr.co.webcash.repository.PostTagRepository;

@Service
public class PostTagServiceImpl implements PostTagService {
	
	@Autowired private PostTagRepository postTagRepository;

	@Override
	public boolean save(PostTag postTag) {
		return postTagRepository.insert(postTag);
	}

	@Override
	public void save(Post post) {
		List<PostTag> postTagList = post.getPostTagList();
		
		if(postTagList == null || postTagList.isEmpty())	return;
		
		for(PostTag postTag : postTagList){
			postTag.setPost(post);
			this.save(postTag);
		}
	}

	@Override
	public void delete(Post post) {
		postTagRepository.deleteFromPostId(post.getId());
	}

	@Override
	public void update(Post post) {
		postTagRepository.deleteFromPostId(post.getId());
		save(post);
	}

}
