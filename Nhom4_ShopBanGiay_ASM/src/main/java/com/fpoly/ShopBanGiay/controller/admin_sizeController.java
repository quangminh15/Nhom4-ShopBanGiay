package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.ShopBanGiay.model.Size;

import jakarta.validation.Valid;

@Controller
public class admin_sizeController {

	@GetMapping("/admin_size")
	public String admin_size(Size size, Model model) {
		size = new Size();
		model.addAttribute("size",size);
		return "/admin/admin_size";
	}
	
	@PostMapping("/admin_size")
	public String validSize(@Valid @ModelAttribute("size") Size size, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "/admin/admin_size";
		}
		model.addAttribute("size",size);
		return "/admin/admin_size";
	}
}
