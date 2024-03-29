package kr.co.webcash.domain.post.scrap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.notification.Notificable;
import kr.co.webcash.domain.post.Post;


public class Scrap implements Notificable {
	private Post post;
	private ScrapTarget target;
	
	private static final String PREFIX = "@@";
	private static final String BLOG_ID = "[a-zA-Z0-9]+";
	private static final String POST_DISPLAY_ID = "[0-9]+";
	private static final String POST_REVISION_ID = "[0-9]+";
	private static final String SUFFIX = "##";
	
	private static final String URL_REGEX =	PREFIX 
			+ BLOG_ID + "/" + POST_DISPLAY_ID + "/" + POST_REVISION_ID 
			+ SUFFIX;
	
	public Scrap(){
		this.target = new ScrapTarget();
	}
	
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
			scrap.setTarget(new ScrapTarget(blogId,  postDisplayId, postRevisionId));
//			scrap.target.setBlog(new Blog(blogId));
//			scrap.target.setPostDisplayId(postDisplayId);
			scrap.target.setPostRevisionId(postRevisionId);
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
			.append(target.getBlogId()).append("/")
			.append(target.getPostDisplayId()).append("/")
			.append(target.getPostRevisionId()).append("##");
		
		return builder.toString();
	}
	
	public static String getTag(String blogId, long postDisplayId, long postRevisionId){
		StringBuilder builder = new StringBuilder();
		builder.append("@@")
			.append(blogId).append("/")
			.append(postDisplayId).append("/")
			.append(postRevisionId).append("##");
		
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "Scrap [post=" + post + ", target=" + target + "]";
	}
	
	public String getTargetURI(){		return target.getUri();		}
	
	public String getTargetBlogId() {		return target.getBlogId();	}
	public long getTargetPostDisplayId() {		return target.getPostDisplayId();	}
	public String getTargetPostTitle(){		return target.getPostTitle();	}
	public String getTargetPostContents() {		return target.getPostContents();	}
	public long getTargetPostRevisionId() {		return target.getPostRevisionId();	}
	
	
	public Post getPost() {		return post;	}
	public void setPost(Post post) {		this.post = post;	}
	public ScrapTarget getTarget() {		return target;	}
	public void setTarget(ScrapTarget target) {		this.target = target;	}

	@Override
	public Blog getNotificationBlog() {
		return post.getBlog();
	}

	@Override
	public String getNotificationContents() {
		StringBuilder builder = new StringBuilder();
		
		builder
			.append("<a href=\"").append(post.getURI()).append("\">")
				.append("[").append(post.getTitle()).append("]")
			.append("</a>")
			.append(" 글에서 스크랩 한 ")
			.append("<a href=\"").append(target.getUri()).append("\">")
				.append("[").append(target.getBlogTitle()).append(":").append(target.getPostTitle()).append("]")
			.append("</a>")
			.append(" 글이 업데이트 되었습니다. ")
			.append("<a href=\"")
				.append("/").append(target.getBlogId()).append("/").append(target.getPostDisplayId())
				.append("/compare/").append(target.getPost().getCurrentRevision().getDisplayId()).append("/").append(target.getPostRevisionId())
				.append("\">")
				.append("변경내역 보기")
			.append("</a>");
		
		return builder.toString();
	}
}
