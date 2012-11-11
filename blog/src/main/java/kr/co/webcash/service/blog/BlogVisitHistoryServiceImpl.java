package kr.co.webcash.service.blog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.blog.BlogVisitHistory;
import kr.co.webcash.repository.BlogVisitHistoryRepository;

@Service
public class BlogVisitHistoryServiceImpl implements BlogVisitHistoryService{
	
	@Autowired private BlogVisitHistoryRepository visitHistoryRepository;

	@Override
	public boolean save(BlogVisitHistory blogVisitHistory) {
		visitHistoryRepository.insert(blogVisitHistory);
		return true;
	}
	
	private Map<String, Object> getTodayCondition(){
		Map<String, Object> params = new HashMap<String, Object>();
		
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
			
			params.put("startDate", format.parseObject(startDateBuilder.toString()));
			params.put("endDate", format.parseObject(endDateBuilder.toString()));
		}catch(Exception e){
		}
		
		return params;
	}

	@Override
	public long countToday(Blog blog) {
		try{
			Map<String, Object> params = getTodayCondition();
			params.put("blogId", blog.getId());
			
			return visitHistoryRepository.countByBlogIdAndFromStartDateToEndDate(params);
		}catch(Exception e){
		}
		
		return -1;
	}

	@Override
	public boolean isVisitToday(BlogVisitHistory blogVisitHistory) {
		try{
			Map<String, Object> params = getTodayCondition();
			params.put("blogId", blogVisitHistory.getBlog().getId());
			params.put("connectIPAddress", blogVisitHistory.getConnectIPAddress());
			
			if(visitHistoryRepository.findByBlogIdAndConnectIPAddressAndFromStartDateToEndDate(params) != null) 	return true;
		}catch(Exception e){
		}
		
		return false;
	}
}
