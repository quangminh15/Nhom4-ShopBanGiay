package com.fpoly.ShopBanGiay.service;

import java.io.File;
import java.io.IOException;
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
}
