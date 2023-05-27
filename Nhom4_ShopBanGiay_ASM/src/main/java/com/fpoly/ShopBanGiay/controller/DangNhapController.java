package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.ShopBanGiay.model.NguoiDung;
import jakarta.validation.Valid;

@Controller
public class DangNhapController {

	@GetMapping("/dangnhap")
	public String DangNhap(NguoiDung nguoidung, Model model) {
		nguoidung = new NguoiDung();
		model.addAttribute("nguoidung", nguoidung);
		return "/nguoidung/dangnhap";
	}

	@PostMapping("/dangnhap")
	public String validDangNhap(@Valid @ModelAttribute("nguoidung") NguoiDung nguoidung, BindingResult result,
			Model model) {
		if (nguoidung.getEmail().equals("nguoidung@gmail.com") && nguoidung.getMatKhau().equals("12345")) {
			return "/nguoidung/trangchu";
		}
		if (nguoidung.getEmail().equals("admin@gmail.com") && nguoidung.getMatKhau().equals("12345")) {
			return "/admin/admin_nguoidung";
		}

		if (result.hasErrors()) {
			return "/nguoidung/dangnhap";
		}
		model.addAttribute("nguoidung", nguoidung);
		return "/nguoidung/dangnhap";
	}

}
