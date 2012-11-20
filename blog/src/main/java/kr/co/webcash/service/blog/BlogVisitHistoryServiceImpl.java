package kr.co.webcash.service.blog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
			endDateBuilder.append(year).append("-").append(month).append("-").append(day+1);
			
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

	@Override
	public ArrayList test(String blogId) {
		
		Calendar today = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long count=0;
		int lastDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList al = new ArrayList();
		
		List l = visitHistoryRepository.select(blogId);	//방문날짜 리스트
		for(int i=1; i<=lastDay; i++){
			today.set(Calendar.DAY_OF_MONTH, i);
			date = today.getTime();
			String s = format.format(date);
			for(int j=0; j<l.size(); j++){
				while(s.equals(l.get(j))){
					count++;
					break;
				}
			}
			al.add(count);
			count=0;
		}
		return al;
	}
}
