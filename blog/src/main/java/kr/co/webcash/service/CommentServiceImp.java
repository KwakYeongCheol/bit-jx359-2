package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.Guestbook;
import kr.co.webcash.domain.Notification;
import kr.co.webcash.domain.User;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.CommentRepository;
import kr.co.webcash.repository.GuestbookRepository;
import kr.co.webcash.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired CommentRepository commentRepository;
	@Autowired PostService postService;
	@Autowired GuestbookService guestbookService;
	
	@Autowired private NotificationService notificationService;

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

//	@Override
//	public long findLastIdByBlogIdAndTargetIdAndType(String blogId, long targetId, String type) {
//		Comment comment = commentRepository.findLastByBlogIdAndTargetIdAndType(blogId, targetId, type);
//		
//		if(comment == null){
//			return 0;
//		}
//		
//		return comment.getId();
//	}


//	@Override
//	public Comment findByIdAndBlogIdAndTargetIdAndType(long id, String blogId, long targetId, String type) {
//		return commentRepository.findByIdAndBlogIdAndTargetIdAndType(id, blogId, targetId, type);
//	}

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
	public void sendNotification(User loginUser, String blogId, long targetDisplayId, CommentType commentType) {
		Notification notification = new Notification();
		notification.setBlog(new Blog(blogId));
		
		switch(commentType){
		case post:
			notification.setUri("/" + blogId + "/" + targetDisplayId);
			break;
		case guestbook:
			notification.setUri("/" + blogId + "/guestbook/" + targetDisplayId);
			break;
		}
		
		StringBuilder message = new StringBuilder();
		message.append(loginUser.getName()).append("님이 댓글을 남겼습니다.");
		
		notification.setContents(message.toString());
		
		notificationService.sendNotification(notification);
	}
	
}
