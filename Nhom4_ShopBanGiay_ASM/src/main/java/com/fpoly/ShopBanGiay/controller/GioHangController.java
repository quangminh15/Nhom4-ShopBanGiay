package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GioHangController {

	@GetMapping("/giohang")
	public String GioHang() {
		return "/nguoidung/giohang";
	}
}
