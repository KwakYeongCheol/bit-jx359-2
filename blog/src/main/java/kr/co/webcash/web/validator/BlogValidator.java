package kr.co.webcash.web.validator;

import java.util.regex.Pattern;

import kr.co.webcash.domain.blog.Blog;
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
			
			if(blog.getId() != null){
				if(blog.getId().length() < 5)		errors.rejectValue("id", "field.minlength.blog.id");
				
				String blogIdRegex = "^[a-zA-Z]+[a-zA-Z0-9]+";
				Pattern pattern = Pattern.compile(blogIdRegex);
				if(!pattern.matcher(blog.getId()).find())	errors.rejectValue("id", "field.validate.blog.id");
				
				Blog findBlog = blogService.findById(blog.getId());
				if(findBlog != null)	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "duplicate.blog.id");
			}
			
			if(blog.getTitle() != null){
				if(blog.getTitle().length() < 5)	errors.rejectValue("title", "field.minlength.blog.title");
				
				if(blog.getTitle().length() > 15)	errors.rejectValue("title", "field.maxlength.blog.title");
			}
			
		}
	}
}
