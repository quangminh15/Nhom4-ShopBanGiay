package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.ShopBanGiay.model.SanPham;

import jakarta.validation.Valid;

@Controller
public class admin_sanphamController {

	@GetMapping("/admin_sanpham")
	public String admin_sanpham(SanPham sanpham, Model model) {
		sanpham = new SanPham();
		model.addAttribute("sanpham",sanpham);
		return "/admin/admin_sanpham";
	}
	
	@PostMapping("/admin_sanpham")
	public String validSize(@Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "/admin/admin_sanpham";
		}
		model.addAttribute("sanpham",sanpham);
		return "/admin/admin_sanpham";
	}
}
