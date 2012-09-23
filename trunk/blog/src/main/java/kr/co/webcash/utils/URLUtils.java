package kr.co.webcash.utils;

public class URLUtils {
	
	public static String getRequestURL(String requestURI, String contextPath) {
		if(contextPath.length() == 1)		return requestURI;
		
		int contextPathLength = contextPath.length();
		
		return requestURI.substring(contextPathLength);
	}

	public static String getBlogId(String requestURL, String contextPath) {
		int contextPathLength = contextPath.length();
		
		int beginIndex = contextPathLength;
		if(contextPathLength > 1){
			beginIndex = beginIndex + 1;
		}
		
		int endIndex = requestURL.indexOf("/", beginIndex);
		
		if(endIndex == -1)		return requestURL.substring(beginIndex);
		else					return requestURL.substring(beginIndex, endIndex);
	}

}
