package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {
	
	@GetMapping("/trangchu")
	public String DangNhap() {
		return "trangchu";
	}
}
