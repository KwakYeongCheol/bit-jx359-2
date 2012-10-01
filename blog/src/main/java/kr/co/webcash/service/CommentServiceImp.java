package kr.co.webcash.service;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired CommentRepository commentRepository;

	@Override
	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public long findLastIdByBlogIdAndTargetIdAndType(String blogId, long targetId, String type) {
		Comment comment = commentRepository.findLastByBlogIdAndTargetIdAndType(blogId, targetId, type);
		
		if(comment == null){
			return 0;
		}
		
		return comment.getId();
	}

	@Override
	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}

	@Override
	public void update(Comment comment) {
		commentRepository.update(comment);
	}

	@Override
	public Comment findByIdAndBlogIdAndTargetIdAndType(long id, String blogId, long targetId, String type) {
		return commentRepository.findByIdAndBlogIdAndTargetIdAndType(id, blogId, targetId, type);
	}
	
}
