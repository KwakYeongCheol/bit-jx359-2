package kr.co.webcash.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.TagCategory;

public class TagCategoryRepository {
	
	private static Map<String, List<String>> dictionary = new HashMap<String, List<String>>();
	
	static{
		List<String> itDictionary = Arrays.asList(
			"java", "자바", "아이폰", "애플", "안드로이드", "노트북", "카메라"
		);
		
		List<String> tourDictionary = Arrays.asList(
			"제주도", "렌트", "1박2일"
		);
		
		dictionary.put(TagCategory.it.toString(), itDictionary);
		dictionary.put(TagCategory.tour.toString(), tourDictionary);
	}
	

	public static List<String> findAll(TagCategory tagCategory) {
		return dictionary.get(tagCategory.toString());
	}

}
