package kr.co.webcash.service.comment;

import java.util.List;

import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.comment.Comment;
import kr.co.webcash.domain.comment.CommentType;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.CommentRepository;
import kr.co.webcash.service.GuestbookService;
import kr.co.webcash.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService{
	@Autowired CommentRepository commentRepository;
	@Autowired PostService postService;
	@Autowired GuestbookService guestbookService;
	
	@Override
	public void save(Comment comment) {
		Comment lastComment = commentRepository.findLast();
		
		if(lastComment == null){
			comment.setId(1);
		}else{
			comment.setId(lastComment.getId() + 1);
		}
		
		commentRepository.save(comment);
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
	public long getTargetIdByBlogIdAndCommentTypeAndDisplayId(String blogId, CommentType type, long targetDisplayId) {
		switch(type){
		case post:
			Post post = postService.findByBlogIdAndDisplayId(blogId, targetDisplayId);
			return post.getId();
		case guestbook:
			Guestbook guestbook = guestbookService.findByBlogIdAndDisplayId(blogId, targetDisplayId);
			return guestbook.getId();
		}
		
		return -1;
	}

	@Override
	public long findLastDisplayIdByBlogIdAndCommentType(String blogId, CommentType type) {
		Comment comment = commentRepository.findLastByBlogIdAndCommentType(blogId, type);
		
		if(comment == null){
			return 0;
		}
		
		return comment.getDisplayId();
	}

	@Override
	public Comment findByTargetIdAndCommentTypeAndDisplayId(long targetId, CommentType type, long displayId) {
		return commentRepository.findByDisplayIdAndTargetIdAndType(displayId, targetId, type.toString());
	}

	@Override
	public List<Comment> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize) {
		return commentRepository.findAllByBlogIdAndPageNumberAndPageSize(blogId, pageNumber, pageSize);
	}

	@Override
	public int countByBlogId(String blogId) {
		return commentRepository.countByBlogId(blogId);
	}

	@Override
	public List<Comment> listByBlogIdAndPage(String blogId, Page page) {
		return commentRepository.findAllByBlogIdAndPageNumberAndPageSize(blogId, page.getCurrentPage(), page.getPageSize());
	}

	@Override
	public void delete(Post post) {
		commentRepository.deleteFromTargetIdAndCommentType(post.getId(), CommentType.post);
	}
}
