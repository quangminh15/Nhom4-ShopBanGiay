package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_danhmucsanphamController {

	@GetMapping("/admin_danhmucsanpham")
	public String admin_danhmucsanpham() {
		return "/admin/admin_danhmucsanpham";
	}
}
