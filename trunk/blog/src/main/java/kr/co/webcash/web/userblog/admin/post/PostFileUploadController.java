package kr.co.webcash.web.userblog.admin.post;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import kr.co.webcash.utils.ImageUploadUtils;
import kr.co.webcash.utils.URLUtils;
import kr.co.webcash.web.security.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/{blogId}/admin/post")
public class PostFileUploadController {
	
	@Inject	private Provider<LoginUser> loginUserProvider;
	
	@Autowired private ImageUploadUtils imageUploadUtils;
	
	@RequestMapping("/imageuploader")
	public String imageUploaderForm(){
		return "/userblog/admin/post/imageuploader";
	}
	
	
	@RequestMapping(value = "/imageupload", method = RequestMethod.POST)
	@ResponseBody
	public Map imageUpload(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("image");
		
		if(multipartFile != null && !multipartFile.isEmpty()){
			try{
				String fileName = imageUploadUtils.uploadImageFromMultipartFile(multipartFile, "tmp");
				
				String imageUrl = URLUtils.make(
						request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(),
						"resources", "images", "tmp", fileName);
				
				result.put("success", true);
				result.put("url", imageUrl);
			}catch(Exception e){
				result.put("success", false);
				result.put("message", "File upload failed.");
			}
		}
		
		return result;
	}
}
