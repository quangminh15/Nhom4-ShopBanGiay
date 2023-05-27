package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_donhangController {

	@GetMapping("/admin_donhang")
	public String admin_donhang() {
		return "/admin/admin_donhang";
	}
}
