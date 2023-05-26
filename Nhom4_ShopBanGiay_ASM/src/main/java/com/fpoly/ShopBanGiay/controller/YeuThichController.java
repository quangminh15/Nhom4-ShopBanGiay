package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YeuThichController {

	@GetMapping("/yeuthich")
	public String DangNhap() {
		return "/nguoidung/yeuthich";
	}
}
