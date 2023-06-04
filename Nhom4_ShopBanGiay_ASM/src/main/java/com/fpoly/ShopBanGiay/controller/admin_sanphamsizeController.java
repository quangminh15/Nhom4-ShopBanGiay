package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.ShopBanGiay.model.SanPhamSize;

import jakarta.validation.Valid;

@Controller
public class admin_sanphamsizeController {

	@GetMapping("/admin_sanphamsize")
	public String admin_sanphamsize(SanPhamSize sanphamsize, Model model) {
		sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize",sanphamsize);
		return "admin/admin_sanphamsize";
	}
	
	@PostMapping("/admin_sanphamsize")
	public String validSize(@Valid @ModelAttribute("sanphamsize") SanPhamSize sanphamsize, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "admin/admin_sanphamsize";
		}
		model.addAttribute("sanphamsize",sanphamsize);
		return "admin/admin_sanphamsize";
	}
}
