package kr.co.webcash.web.validator;

import kr.co.webcash.domain.Blog;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BlogValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Blog.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Blog blog = (Blog)target;
		
		if(blog != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required.blog.title");
		}
	}
}