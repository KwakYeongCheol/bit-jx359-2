package kr.co.webcash.web.validator;


import kr.co.webcash.domain.Comment;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Comment comment = (Comment)target;
		
		if(comment != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contents", "field.required.comment.contents");
		}
	}
}
