package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_sanphamController {

	@GetMapping("/admin_sanpham")
	public String admin_sanpham() {
		return "/admin/admin_sanpham";
	}
}
