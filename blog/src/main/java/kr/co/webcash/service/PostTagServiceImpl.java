package kr.co.webcash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.post.PostTag;
import kr.co.webcash.repository.PostTagRepository;

@Service
public class PostTagServiceImpl implements PostTagService {
	
	@Autowired private PostTagRepository postTagRepository;

	@Override
	public boolean save(PostTag postTag) {
		return postTagRepository.insert(postTag);
	}

}
