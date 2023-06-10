package com.fpoly.ShopBanGiay.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest req;
	public String getParameterValue(String name, String defaultValue) {
	    String value = req.getParameter(name);
	    return value != null ? value : defaultValue;
	}
	public int getIntegerParameterValue(String name, int defaultValue) {
	    String value = req.getParameter(name);
	    if (value != null) {
	        try {
	            return Integer.parseInt(value);
	        } catch (NumberFormatException e) {
	            
	        }
	    }
	    return defaultValue;
	}
	
	public boolean getBooleanParameterValue(String name, boolean defaultValue) {
	    String value = req.getParameter(name);
	    if (value != null) {
	        try {
	            return Boolean.parseBoolean(value);
	        } catch (NumberFormatException e) {
	            
	        }
	    }
	    return defaultValue;
	}
	
	public boolean getBoolean(String name, boolean defaultValue) {
        String value = req.getParameter(name);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }
	
	public Date getDateParameterValue(String name, String pattern) {
	    String value = req.getParameter(name);
	    if (value != null) {
	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	            return dateFormat.parse(value);
	        } catch (ParseException e) {
	            throw new RuntimeException("Sai định dạng thời gian", e);
	        }
	    }
	    return null;
	}
	public double getDoubleParameterValue(String name, double defaultValue) {
	    String value = req.getParameter(name);
	    if (value != null) {
	        try {
	            return Double.parseDouble(value);
	        } catch (NumberFormatException e) {
	            
	        }
	    }
	    return defaultValue;
	}
	public File save(MultipartFile file, String path) {
	    if (!file.isEmpty()) {
	        try {
	            String fileName = file.getOriginalFilename();
	            String filePath = path + File.separator + fileName;
	            File destination = new File(filePath);
	            file.transferTo(destination);
	            return destination;
	        } catch (IOException e) {
	            throw new RuntimeException("Lỗi lưu file", e);
	        }
	    }
	    return null;
	}
	
	public static void saveFile(String uploadDir, String filename, MultipartFile multipartfile) throws IOException{
		Path path = Paths.get(uploadDir);
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
		
		try(InputStream inputStream = multipartfile.getInputStream()) {
			Path filePath = path.resolve(filename);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new IOException("Lưu thất bại file "+ filename);
		}
	}
	/**
     * Lưu file upload vào thư mục
     * @param file chứa file upload từ client
     * @param path đường dẫn tính từ webroot
     * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
     * @throws RuntimeException lỗi lưu file
 */
		public File saveV2(MultipartFile file, String path, HttpServletRequest request) {
		    if (file != null && !file.isEmpty()) {
		        try {
		            String webRootPath = request.getServletContext().getRealPath("/");
		            String savePath = webRootPath + path; //Xây dựng đường dẫn lưu trữ file bằng cách kết hợp đường dẫn thư mục gốc và path truyền vào. path là đường dẫn tương đối tính từ thư mục gốc.
		            File directory = new File(savePath); //Tạo một đối tượng File đại diện cho thư mục lưu trữ file.
		            if (!directory.exists()) { //Kiểm tra xem thư mục lưu trữ file đã tồn tại hay chưa. Nếu không tồn tại, phương thức mkdirs() được gọi để tạo các thư mục con theo đường dẫn nếu cần thiết.
		                directory.mkdirs();
		            }
		            String fileName = file.getOriginalFilename(); // Lấy tên file + định dạng file
		            String filePath = savePath + File.separator + fileName; // Đầy dủ 1 đường dẫn file
		            File savedFile = new File(filePath);
		            file.transferTo(savedFile);
		            return savedFile;
		        } catch (IOException e) {
		            // Xử lý lỗi lưu file
		            e.printStackTrace();
		            throw new RuntimeException("Lỗi lưu file");
		        }
		    }
		    return null;
		}
}
