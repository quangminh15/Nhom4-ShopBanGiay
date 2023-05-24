package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangNhapController {
	
	@GetMapping("/dangnhap")
	public String DangNhap() {
		return "dangnhap";
	}
}
