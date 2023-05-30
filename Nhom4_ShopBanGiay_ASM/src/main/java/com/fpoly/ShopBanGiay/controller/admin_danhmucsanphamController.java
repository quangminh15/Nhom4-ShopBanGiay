package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.ShopBanGiay.model.DanhMuc;


import jakarta.validation.Valid;

@Controller
public class admin_danhmucsanphamController {

	@GetMapping("/admin_danhmucsanpham")
	public String admin_danhmucsanpham(DanhMuc danhmuc, Model model) {
		danhmuc = new DanhMuc();
		model.addAttribute("danhmuc",danhmuc);
		
		return "/admin/admin_danhmucsanpham";
	}
	
	@PostMapping("/admin_danhmucsanpham")
	public String validSize(@Valid @ModelAttribute("danhmuc") DanhMuc danhmuc, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "/admin/admin_danhmucsanpham";
		}
		model.addAttribute("danhmuc",danhmuc);
		return "/admin/admin_danhmucsanpham";
	}
}
