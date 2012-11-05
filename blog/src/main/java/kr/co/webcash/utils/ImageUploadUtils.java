package kr.co.webcash.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploadUtils {
	
	private String imageUploadDirectory = "/upload/images";

	public String uploadImageFromMultipartFile(MultipartFile multipartFile, String userId) {
		try{
			File homeDirectory = new File(imageUploadDirectory);
			File userHomeDirectory = new File(homeDirectory, userId);
			
			if(!userHomeDirectory.exists())		userHomeDirectory.mkdir();
			
			String saveFileName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
			
			InputStream inputStream = multipartFile.getInputStream();
			
			FileOutputStream fos = new FileOutputStream(new File(userHomeDirectory, saveFileName));
			
			byte[] buffer = new byte[2048];
			int length = -1;
			
			while((length = inputStream.read(buffer)) != -1){
				fos.write(buffer, 0, length);
			}
			
			fos.close();
			
			return saveFileName;
		}catch(Exception e){
			throw new RuntimeException();
		}
	}

	private void createThumbnailImage(File fromFile, File toFile) throws Exception {
		File thumbnailImage = toFile;
		FileInputStream fis = new FileInputStream(fromFile);
		BufferedImage bi = ImageIO.read(fis);
		
		int originalWidth = bi.getWidth();
		int originalHeight = bi.getHeight();
		
		int width = -1;
		int height = -1;
		
		if(originalWidth > originalHeight){
			width = 100;
			height = (originalHeight * 100)/originalWidth;
		}else{
			width = (originalWidth * 100)/originalHeight;
			height = 100;
		}
		
		if(width == -1 || height == -1){
			width = 100; height = 100;
		}
		
		BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = thumb.createGraphics();
		
		g2.drawImage(bi, 0, 0, width, height, null);
		ImageIO.write(thumb, "jpg", thumbnailImage);
	}

}
