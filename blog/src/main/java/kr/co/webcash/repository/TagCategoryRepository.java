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
			"java", "자바", "아이폰", "애플", "안드로이드", "노트북", "카메라", "it", "Ubuntu", "SK", "TCP", "UDP", "소켓", "윈도우"
		);
		
		List<String> tourDictionary = Arrays.asList(
			"제주도", "렌트", "1박2일"
		);
		
		List<String> bookDictionary = Arrays.asList(
			"도서", "책", "안철수", "서른", "불안", "이야기", "소설", "운동", "화장", "청춘", "아프다", "생각", "악마", "비타민"
		);
		
		dictionary.put(TagCategory.it.toString(), itDictionary);
		dictionary.put(TagCategory.tour.toString(), tourDictionary);
		dictionary.put(TagCategory.book.toString(), bookDictionary);
	}
	

	public static List<String> findAll(TagCategory tagCategory) {
		return dictionary.get(tagCategory.toString());
	}

}
