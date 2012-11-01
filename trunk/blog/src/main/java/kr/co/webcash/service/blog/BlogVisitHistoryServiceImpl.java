package kr.co.webcash.service.blog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.BlogVisitHistory;
import kr.co.webcash.repository.BlogVisitHistoryRepository;

@Service
public class BlogVisitHistoryServiceImpl implements BlogVisitHistoryService{
	
	@Autowired private BlogVisitHistoryRepository visitHistoryRepository;

	@Override
	public boolean save(BlogVisitHistory blogVisitHistory) {
		visitHistoryRepository.insert(blogVisitHistory);
		return true;
	}

	@Override
	public long countToday(Blog blog) {
		try{
			Calendar today = Calendar.getInstance();
			
			int year = today.get(Calendar.YEAR);
			int month = today.get(Calendar.MONTH) + 1;
			int day = today.get(Calendar.DAY_OF_MONTH);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
			
			StringBuilder startDateBuilder = new StringBuilder();
			startDateBuilder.append(year).append("-").append(month).append("-").append(day);
			
			StringBuilder endDateBuilder = new StringBuilder();
			endDateBuilder.append(year).append("-").append(month).append("-").append(day + 1);
			
			Map params = new HashMap();
			params.put("blogId", blog.getId());
			params.put("startDate", format.parseObject(startDateBuilder.toString()));
			params.put("endDate", format.parseObject(endDateBuilder.toString()));
			
			return visitHistoryRepository.countByBlogIdAndFromStartDateToEndDate(params);
		}catch(Exception e){
		}
		
		return -1;
	}
}
