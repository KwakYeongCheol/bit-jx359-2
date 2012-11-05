package kr.co.webcash.utils;

public class URLUtils {
	
	public static String getRequestURL(String requestURI, String contextPath) {
		if(contextPath.length() == 1)		return requestURI;
		
		int contextPathLength = contextPath.length();
		
		return requestURI.substring(contextPathLength);
	}

	public static String getBlogId(String requestURL, String contextPath) {
		int contextPathLength = contextPath.length();
		
		int beginIndex = contextPathLength + 1;
		
		int endIndex = requestURL.indexOf("/", beginIndex);
		
		if(endIndex == -1)		return requestURL.substring(beginIndex);
		else					return requestURL.substring(beginIndex, endIndex);
	}

	public static String make(String scheme, String serverName, int serverPort, String contextPath) {
		StringBuilder builder = new StringBuilder();
		builder.append(scheme).append("://").append(serverName).append(":").append(serverPort).append("/").append(contextPath);
		
		return builder.toString();
	}
	
	public static String make(String scheme, String serverName, int serverPort, String contextPath, String... paths){
		StringBuilder builder = new StringBuilder(make(scheme, serverName, serverPort, contextPath));
		
		for(String path : paths){
			builder.append(path).append("/");
		}
		
		return builder.toString();
	}
}
