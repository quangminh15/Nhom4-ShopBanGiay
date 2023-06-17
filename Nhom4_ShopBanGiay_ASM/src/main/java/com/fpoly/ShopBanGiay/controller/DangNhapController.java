package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

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
import com.fpoly.ShopBanGiay.service.CookieService;
import com.fpoly.ShopBanGiay.service.ParamService;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class DangNhapController {
	
	@Autowired
	CookieService cookieService;

	@Autowired
	ParamService paramService;

	@Autowired
	SessionService sessionService;
	
	@Autowired 
	NguoiDungDAO nguoidungDAO;

	@GetMapping("/dangnhap")
	public String DangNhap(NguoiDung nguoidung, Model model) {
		nguoidung = new NguoiDung();
		if(cookieService.getCookieValue("email") != null && cookieService.getCookieValue("pass") != null) {
			nguoidung.setEmail(cookieService.getCookieValue("email").toString());
			nguoidung.setMatkhau(cookieService.getCookieValue("pass").toString());
			System.out.println("Email: "+cookieService.getCookieValue("email"));
			System.out.println("Pass: "+cookieService.getCookieValue("pass"));
			model.addAttribute("nguoidung", nguoidung);
			model.addAttribute("remember", true);
		}
		model.addAttribute("nguoidung", nguoidung);
		return "/nguoidung/dangnhap";
	}

//	@PostMapping("/dangnhap")
//	public String validDangNhap(@Valid @ModelAttribute("nguoidung") NguoiDung nguoidung, BindingResult result,
//			Model model) {
//		if (nguoidung.getEmail().equals("nguoidung@gmail.com") && nguoidung.getMatkhau().equals("12345")) {
//			return "/nguoidung/trangchu";
//		}
//		if (nguoidung.getEmail().equals("admin@gmail.com") && nguoidung.getMatkhau().equals("12345")) {
//			return "/admin/admin_nguoidung";
//		}
//
//		if (result.hasErrors()) {
//			return "/nguoidung/dangnhap";
//		}
//		model.addAttribute("nguoidung", nguoidung);
//		return "/nguoidung/dangnhap";
//	}
	
	@PostMapping("/loginConfirm")
	public String DangNhap1(Model model, @ModelAttribute("user") NguoiDung u, @RequestParam("pass") String pass) {
		model.addAttribute("nguoidung", new NguoiDung());
		System.out.println("u: "+u);
		if(u.getEmail().equals("")) {
			model.addAttribute("messageLoginFail", "Lỗi để trống Email!");
	        return "/nguoidung/dangnhap";
		}else if(pass.equals("")) {
			NguoiDung ndTemp = new NguoiDung();
			ndTemp.setEmail(u.getEmail());
			model.addAttribute("nguoidung", ndTemp);
			model.addAttribute("messageLoginFail", "Lỗi để trống Mật khẩu!");
	        return "/nguoidung/dangnhap";
		}else {
			// Lấy user đầy đủ
			NguoiDung user = nguoidungDAO.findByEmail(u.getEmail());
			if(user == null) {
				NguoiDung ndTemp = new NguoiDung();
				model.addAttribute("nguoidung", ndTemp);
				model.addAttribute("messageLoginFail", "Thông tin đăng nhập chưa chính xác");
		        return "/nguoidung/dangnhap";
			}
			// Xác thực đăng nhập và kiểm tra thông tin người dùng
		    if (authenticate(user.getEmail(), pass)) {
		        // Lưu thông tin người dùng vào session
		    	System.out.println("Mã: "+user.getMand());
		    	System.out.println("Email: "+user.getEmail());
		    	System.out.println("Vai trò: "+user.isVaitro());
		    	user.setMatkhau(pass);
		    	sessionService.setSessionAttribute("user", user);
		    	System.out.println("--------Đã lưu session từ đăng nhập---------" );
		    	
		    	// Lưu thông tin vào Cookie
		    	boolean remember = paramService.getBoolean("remember", false);System.out.println("Remember: "+remember);
		    	if(remember) {
		    		System.out.println("Đã tick remember");
		    		cookieService.addCookie("email", u.getEmail(), 2);
		    		cookieService.addCookie("pass", pass, 2);
		    	}else {
		    		System.out.println("Không tick remember");
		    		cookieService.removeCookie("email");
		    		cookieService.removeCookie("pass");
		    	}
		    	   	
		        // Đăng nhập thành công, chuyển hướng đến trang chính
		    	if(user.isVaitro()) {
		    		return "redirect:/admin/admin_nguoidung";
		    	}else {
		    		return "redirect:/trangchu";
		    	}
		    } else {
		        // Đăng nhập không thành công, xử lý lỗi hoặc hiển thị thông báo
		    	model.addAttribute("messageLoginFail", "Thông tin đăng nhập chưa chính xác");
		        return "/nguoidung/dangnhap";
		    }
		}
		
	}

	public boolean authenticate(String Email, String pass) {
		List<NguoiDung> list =  nguoidungDAO.findForAuthenticate(Email, pass);
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	// Đăng xuất 
	@RequestMapping("/logout")
	public String logout(Model model) {
		sessionService.removeSessionAttribute("user");
		System.out.println("Đã xoá session - Đăng xuất");
		return "redirect:/dangnhap";
	}
	
}
