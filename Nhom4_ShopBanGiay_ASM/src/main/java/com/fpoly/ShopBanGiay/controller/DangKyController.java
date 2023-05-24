package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangKyController {
	
	@GetMapping("/dangky")
	public String DangNhap() {
		return "dangky";
	}
}
