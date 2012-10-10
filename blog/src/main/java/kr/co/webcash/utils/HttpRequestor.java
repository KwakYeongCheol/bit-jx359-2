package kr.co.webcash.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public final class HttpRequestor {
	public static final String CRLF = "\r\n";
	
	private URL targetURL;
	
	private ArrayList parameterList;
	
	
	public HttpRequestor(URL target){
		this(target, 20);
	}
	
	public HttpRequestor(URL target, int capacity){
		this.targetURL = target;
		this.parameterList = new ArrayList(capacity);
	}
	
	public void addParameter(String parameterName, String parameterValue){
		if(parameterValue == null)	throw new IllegalArgumentException("Parameter Value can't be null");
		
		parameterList.add(parameterName);
		parameterList.add(parameterValue);
	}
	
	public void addFile(String parameterName, File parameterValue){
		
		if(parameterValue == null){
			parameterList.add(parameterName);
			parameterList.add(new NullFile());
		}else{
			parameterList.add(parameterName);
			parameterList.add(parameterValue);
		}
	}
	
	private static String encodeString(ArrayList parameters){
		StringBuffer sb = new StringBuffer(256);
		
		Object[] obj = new Object[parameters.size()];
		parameters.toArray(obj);
		
		for(int i = 0; i < obj.length; i+=2){
			
			if(obj[i+1] instanceof File || obj[i+1] instanceof NullFile)	continue;
			
			sb.append(URLEncoder.encode((String)obj[i]));
			sb.append('=');
			sb.append(URLEncoder.encode((String)obj[i+1]));
			
			if(i+2 < obj.length)	sb.append('&');
		}
		
		return sb.toString();
	}
	
	public InputStream sendGet() throws IOException{
		String paramString = null;
		
		if(parameterList.size() > 0)
			paramString = "?" + encodeString(parameterList);
		else
			paramString = "";
		
		URL url = new URL(targetURL.toExternalForm() + paramString);
		URLConnection connection = url.openConnection();
		return connection.getInputStream();
	}
	
	
	public InputStream sendPost() throws IOException{
		String paramString = null;
		if(parameterList.size() > 0)
			paramString = encodeString(parameterList);
		else
			paramString = "";
		
		
		HttpURLConnection conn = (HttpURLConnection) targetURL.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		
		DataOutputStream out = null;
		try{
			out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(paramString);
			out.flush();
		}finally{
			if(out != null)		out.close();
		}
		
		return conn.getInputStream();
	}
	
	public InputStream sendMultipartPost() throws IOException{
		HttpURLConnection conn = (HttpURLConnection) targetURL.openConnection();
		
		String delimeter = makeDelimeter();
		byte[] newLineBytes = CRLF.getBytes();
		byte[] delimeterBytes = delimeter.getBytes();
		byte[] dispositionBytes = "Content-Disposition: form-data; name=".getBytes();
		byte[] quotationBytes = "\"".getBytes();
		byte[] contentTypeBytes = "Content-Type: application/octet-stream".getBytes();
		byte[] fileNameBytes = "; filename=".getBytes();
		byte[] twoDashBytes = "--".getBytes();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + delimeter);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		
		BufferedOutputStream out = null;
		
		try{
			out = new BufferedOutputStream(conn.getOutputStream());
			
			Object[] obj = new Object[parameterList.size()];
			parameterList.toArray(obj);
			
			for(int i = 0; i < obj.length; i+= 2){
				// Delimeter 전송
				out.write(twoDashBytes);
				out.write(delimeterBytes);
				out.write(newLineBytes);
				
				//파라미터 이름 출력
				out.write(dispositionBytes);
				out.write(quotationBytes);
				out.write(((String)obj[i]).getBytes());
				out.write(quotationBytes);
				
				if(obj[i+1] instanceof String){
					// String 이라면
					out.write(newLineBytes);
					out.write(newLineBytes);
					
					//값 출력
					out.write(((String)obj[i+1]).getBytes());
					out.write(newLineBytes);
				}else{
					if(obj[i+1] instanceof File){
						File file = (File)obj[i+1];
						
						out.write(fileNameBytes);
						out.write(quotationBytes);
						out.write(file.getAbsolutePath().getBytes());
						out.write(quotationBytes);
					}else{
						out.write(fileNameBytes);
						out.write(quotationBytes);
						out.write(quotationBytes);
					}
					
					out.write(newLineBytes);
					out.write(contentTypeBytes);
					out.write(newLineBytes);
					out.write(newLineBytes);
					
					if(obj[i+1] instanceof File){
						File file = (File)obj[i+1];
						BufferedInputStream is = null;
						try{
							is = new BufferedInputStream(new FileInputStream(file));
							byte[] fileBuffer = new byte[1024 * 8];
							int length = -1;
							while((length = is.read(fileBuffer)) != -1){
								out.write(fileBuffer, 0, length);
							}
						}finally{
							if(is != null)	try{ is.close(); } catch(IOException ex){}
						}
					}
					
					out.write(newLineBytes);
				}
				
				if(i + 2 == obj.length){
					out.write(twoDashBytes);
					out.write(delimeterBytes);
					out.write(twoDashBytes);
					out.write(newLineBytes);
				}
			}
			
			out.flush();
		}finally{
			if(out != null)		out.close();
		}
		
		return conn.getInputStream();
	}
	
	
	private static String makeDelimeter() {
        return "---------------------------7d115d2a20060c";
    }
	
	
	private class NullFile{
		NullFile(){
		}
		
		public String toString(){
			return "";
		}
	}
}