package kr.co.webcash.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Scrap {
	private long postId;
	private Blog scrappedBlog;
	private long scrappedPostDisplayId;
	private long scrappedPostRevisionId;
	private String scrappedPostTitle;
	private String scrappedPostContents;
	
	
	private static final String PREFIX = "@@";
	private static final String BLOG_ID = "[a-zA-Z0-9]+";
	private static final String POST_DISPLAY_ID = "[0-9]+";
	private static final String POST_REVISION_ID = "[0-9]+";
	private static final String SUFFIX = "##";
	
	private static final String URL_REGEX =	PREFIX 
			+ BLOG_ID + "/" + POST_DISPLAY_ID + "/" + POST_REVISION_ID 
			+ SUFFIX;
	
	public static List<Scrap> convert(String data){
		return convert(getScrapURLListFromData(data));
	}
	
	public static List<Scrap> convert(List<String> scrapURLList){
		List<Scrap> resultList = new ArrayList<Scrap>(scrapURLList.size());
		
		for(String scrapURL : scrapURLList){
			Scrap scrap = parse(scrapURL);
			if(scrap != null)	resultList.add(scrap);
		}
		
		return resultList;
	}
	
	private static Scrap parse(String scrapURL) {
		String[] arr = scrapURL.split("/");
		int length = arr.length;
		
		if(length != 3)														return null;
		if(!arr[0].contains(PREFIX) || !arr[length - 1].contains(SUFFIX))	return null;
		try{
			String blogId = arr[0].substring(PREFIX.length());
			long postDisplayId = Long.valueOf(arr[1]);
			long postRevisionId = Long.valueOf(arr[2].substring(0, arr[length - 1].length() - SUFFIX.length()));
			
			Scrap scrap = new Scrap();
			scrap.setScrappedBlog(new Blog(blogId));
			scrap.setScrappedPostDisplayId(postDisplayId);
			scrap.setScrappedPostRevisionId(postRevisionId);
			return scrap;
		}catch(Exception e){
			System.out.println(scrapURL + " can not parse to SCRAP. please check your syntax.");
			return null;
		}
		
	}

	private static List<String> getScrapURLListFromData(String data) {
		Set<String> resultSet = new HashSet<String>();
		
		Pattern pattern = Pattern.compile(URL_REGEX);
		Matcher matcher = pattern.matcher(data);
		
		while(matcher.find()){
			resultSet.add(matcher.group());
		}
		
		return new ArrayList<String>(resultSet);
	}

	public String getTag() {
		StringBuilder builder = new StringBuilder();

		builder.append("@@")
			.append(getScrappedBlog().getId()).append("/")
			.append(getScrappedPostDisplayId()).append("/")
			.append(getScrappedPostRevisionId()).append("##");
		
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "Scrap [postId=" + postId + ", scrappedBlog=" + scrappedBlog
				+ ", scrappedPostDisplayId=" + scrappedPostDisplayId
				+ ", scrappedPostRevisionId=" + scrappedPostRevisionId
				+ ", scrappedPostTitle=" + scrappedPostTitle
				+ ", scrappedPostContents=" + scrappedPostContents + "]";
	}
	
	public long getPostId() {		return postId;	}
	public void setPostId(long postId) {		this.postId = postId;	}
	public String getScrappedPostTitle() {		return scrappedPostTitle;	}
	public void setScrappedPostTitle(String scrappedPostTitle) {		this.scrappedPostTitle = scrappedPostTitle;	}
	public String getScrappedPostContents() {		return scrappedPostContents;	}
	public void setScrappedPostContents(String scrappedPostContents) {		this.scrappedPostContents = scrappedPostContents;	}
	public Blog getScrappedBlog() {		return scrappedBlog;	}
	public void setScrappedBlog(Blog scrappedBlog) {		this.scrappedBlog = scrappedBlog;	}
	public long getScrappedPostRevisionId() {		return scrappedPostRevisionId;	}
	public void setScrappedPostRevisionId(long scrappedPostRevisionId) {		this.scrappedPostRevisionId = scrappedPostRevisionId;	}
	public long getScrappedPostDisplayId() {		return scrappedPostDisplayId;	}
	public void setScrappedPostDisplayId(long scrappedPostDisplayId) {		this.scrappedPostDisplayId = scrappedPostDisplayId;	}
}
