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
public class SanPhamController {

	@GetMapping("/sanpham")
	public String SanPham(SanPham sanpham, Model model) {
		sanpham = new SanPham();
		model.addAttribute("sanpham",sanpham);
		return "/nguoidung/sanpham";
	}
	
	@PostMapping("/sanpham")
	public String validSize(@ModelAttribute("sanpham") SanPham sanpham, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "/nguoidung/sanpham";
		}
		model.addAttribute("sanpham",sanpham);
		return "/nguoidung/sanpham";
	}
}
