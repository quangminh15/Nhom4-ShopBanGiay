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
public class quenmatkhauController {
	@GetMapping("/quenmatkhau")
	public String QuenMatKhau(NguoiDung nguoidung, Model model) {
		nguoidung = new NguoiDung();
		model.addAttribute("nguoidung", nguoidung);
		return "/nguoidung/quenmatkhau";
	}
	
	@PostMapping("/quenmatkhauthatbai")
	public String validQuenMatKhau(@Valid @ModelAttribute("nguoidung") NguoiDung nguoidung,BindingResult result, Model model) {
		if (result.hasErrors()) {
		    return "/nguoidung/quenmatkhau";
		  }
		model.addAttribute("nguoidung", nguoidung);
		return "/nguoidung/quenmatkhau";
	}
}
