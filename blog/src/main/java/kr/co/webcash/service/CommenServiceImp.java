package kr.co.webcash.service;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommenServiceImp implements CommentService{
	
	@Autowired CommentRepository commentRepository;

	@Override
	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public int findLastIdByBlogIdAndTargetIdAndType(String blogId, String targetId, String type) {
		Comment comment = commentRepository.findLastByBlogIdAndTargetIdAndType(blogId, targetId, type);
		
		if(comment == null){
			return 0;
		}
		return Integer.parseInt(comment.getId());
	}
	
}
