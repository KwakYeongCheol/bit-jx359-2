package kr.co.webcash.domain;

import kr.co.webcash.repository.TagCategoryRepository;

import org.junit.Test;

public class TagCategoryTest {
	
	@Test
	public void invalid(){
		System.out.println(TagCategoryRepository.findAll(TagCategory.it));
		System.out.println(TagCategoryRepository.findAll(TagCategory.tour));
	}

}
