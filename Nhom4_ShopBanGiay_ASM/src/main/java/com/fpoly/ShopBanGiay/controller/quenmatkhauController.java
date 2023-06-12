package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.MailerService;

import jakarta.validation.Valid;

@Controller
public class quenmatkhauController {
	
	@Autowired 
	NguoiDungDAO dao;
	
	@Autowired
	MailerService mail;
	
	NguoiDung nguoiDungEdit;
	public String messageCheckInputData = "";
	
	@GetMapping("/quenmatkhau")
	public String QuenMatKhau(NguoiDung nguoidung, Model model) {
		model.addAttribute("email", " ");
		return "/nguoidung/quenmatkhau";
	}
	
	@PostMapping("/quenmatkhau/confirm")
	public String validQuenMatKhau(@Valid @ModelAttribute("email") String mail,BindingResult result, Model model) {
		if(!EmailCheckRegex(mail)) {
			model.addAttribute("message", this.messageCheckInputData);
			return "/nguoidung/quenmatkhau";
		}
		if(checkEmailAlreadyExists(mail)) {
			model.addAttribute("message", "Lỗi: Email này không có trong hệ thống");
			return "/nguoidung/quenmatkhau";
		}
		model.addAttribute("message", "show");
		sendMail(mail);
		return "/nguoidung/quenmatkhau";
	}
	
	public boolean EmailCheckRegex(String email) {
		if(email.equals("")) {
			this.messageCheckInputData = "Lỗi: Không được bỏ trống Email!";
			return false;
		}
		String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|gov|edu|vn|us|uk|au|ca)$";
		Pattern EMAIL_PATTERN = Pattern.compile(regexEmail);
		System.out.println("Email: "+email);
		if(!(EMAIL_PATTERN.matcher(email).matches()) || email.length() > 50) {
			this.messageCheckInputData = "Lỗi: Định dạng Email không hợp lệ!";
			return false;
		}
		return true;
	}
	
	public  boolean checkEmailAlreadyExists(String email) {
		List<NguoiDung> list = dao.findByEmails(email);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void sendMail(String email) {
		NguoiDung u = dao.findByEmail(email);
		mail.queue(email, "QUÊN MẬT KHẨU - CONVERSE", "Mật khẩu: "+u.getMatkhau());
	}
	
	public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        
        return sb.toString();
    }
}
