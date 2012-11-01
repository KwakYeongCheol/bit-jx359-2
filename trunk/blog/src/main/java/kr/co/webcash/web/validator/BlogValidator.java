package kr.co.webcash.web.validator;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.service.blog.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BlogValidator implements Validator{
	
	@Autowired BlogService blogService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Blog.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Blog blog = (Blog)target;
		
		if(blog != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required.blog.title");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "field.required.blog.id");
			
			Blog findBlog = blogService.findById(blog.getId());
			if(findBlog != null)	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "duplicate.blog.id");
		}
	}
}
