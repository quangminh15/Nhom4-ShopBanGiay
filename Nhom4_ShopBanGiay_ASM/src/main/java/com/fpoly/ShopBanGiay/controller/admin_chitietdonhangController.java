package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_chitietdonhangController {

	@GetMapping("/admin_chitietdonhang")
	public String admin_chitietdonhang() {
		return "/admin/admin_chitietdonhang";
	}
}
