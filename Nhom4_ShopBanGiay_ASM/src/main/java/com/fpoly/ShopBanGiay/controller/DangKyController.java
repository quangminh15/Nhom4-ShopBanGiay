package com.fpoly.ShopBanGiay.controller;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.MailerService;

import jakarta.validation.Valid;

@Controller
public class DangKyController {
	
	@Autowired
	MailerService mail;
	
	@Autowired
	NguoiDungDAO nguoiDungDAO;
	
	NguoiDung user;
	private String code;
	

	@GetMapping("/dangky")
	public String DangNhap(NguoiDung nguoidung, Model model) {
		nguoidung = new NguoiDung();
		model.addAttribute("nguoidung", nguoidung);
		return "/nguoidung/dangky";
	}
	
	@RequestMapping("/xacnhan")
	public String xacnhan(Model model) {
		System.out.println("Thông tin đk: "+user);
		code = generateCode(6);
		System.out.println("Mã code: "+code);
		mail.queue(user.getEmail(), "ĐĂNG KÝ TÀI KHOẢN CONVERSE", "Mã xác thực: "+code);
		return "/nguoidung/confirmCode";
	}
	
	@RequestMapping("/xacnhanB2")
	public String xacnhanB2(Model model, @RequestParam("code") String codeFormUser) {
		System.out.println("Mã code user nhập: "+codeFormUser);
		System.out.println("Mã code: "+code);
		if(code.equals(codeFormUser)) {
			user.setHinh("150.png");
			user.setDiachi("Việt Nam");
			user.setSdt("0");
			nguoiDungDAO.save(user);
			return "redirect:/dangnhap";
		}
		model.addAttribute("messageConfirmPassWrong", "Mã xác thực chưa đúng!");
		return "/nguoidung/confirmCode";
	}
	
	@PostMapping("/dangkynguoidung")
	public String validDangNhap(@Valid @ModelAttribute("nguoidung") NguoiDung nguoidung,BindingResult result, Model model) {System.out.println("Thông tin đk: "+nguoidung);
		
		if(nguoidung != null) {
			if(!checkEmailAlreadyExists(nguoidung.getEmail())) {
				System.out.println("Email đã tồn tại");
				model.addAttribute("messageConfirmPassWrong", "Lỗi: Email này đã tồn tại!");
				nguoidung.setEmail("");
				model.addAttribute("nguoidung", nguoidung);
				return "/nguoidung/dangky";
			}
			System.out.println("Đang xác thực pass...");
			if(!nguoidung.getMatkhau().equalsIgnoreCase(nguoidung.getHinh())) {
				System.out.println("chưa khớp");
				model.addAttribute("messageConfirmPassWrong", "Lỗi: Mật khẩu và xác nhận mật khẩu chưa khớp!");
				model.addAttribute("nguoidung", nguoidung);
				return "/nguoidung/dangky";
			}
			if(!nguoidung.getMatkhau().equals("") && !nguoidung.getHinh().equals("")) {
				this.user = nguoidung;
				return "redirect:/xacnhan";
			}
		}
		
		if (result.hasErrors()) {
		    return "/nguoidung/dangky";
		}
		
		return "/nguoidung/dangky";
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
	
	public boolean checkEmailAlreadyExists(String email) {
		List<NguoiDung> list = nguoiDungDAO.findByEmails(email);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
}
