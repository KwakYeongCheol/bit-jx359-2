package kr.co.webcash.domain;

import kr.co.webcash.domain.comment.CommentType;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EnumCommentTypeTest {
	
	@Test
	public void post(){
		
		assertThat(CommentType.valueOf("post") , is(CommentType.post));
		
		CommentType type = CommentType.post;
		assertThat(type.toString(), is("post"));
		
		
		
		assertThat(CommentType.valueOf("guestbook"), is(CommentType.guestbook));
		
		type = CommentType.guestbook;
		
		assertThat(type.toString(), is("guestbook"));
	}

}
