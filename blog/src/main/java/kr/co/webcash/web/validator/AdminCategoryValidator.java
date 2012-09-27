package kr.co.webcash.web.validator;

import kr.co.webcash.domain.Category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdminCategoryValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Category category = (Category)target;
		
		if(category != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required.category.title");
		}
	}
}
