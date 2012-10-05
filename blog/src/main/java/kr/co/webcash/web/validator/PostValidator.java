package kr.co.webcash.web.validator;

import kr.co.webcash.domain.Post;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Post.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Post post = (Post)target;
		
		if(post != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required.post.title");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contents", "field.required.post.contents");
			
			
		}
	}
}