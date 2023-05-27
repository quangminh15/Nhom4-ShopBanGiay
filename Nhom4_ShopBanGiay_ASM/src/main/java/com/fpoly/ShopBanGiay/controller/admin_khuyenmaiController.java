package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_khuyenmaiController {

	@GetMapping("/admin_khuyenmai")
	public String admin_khuyenmai() {
		return "/admin/admin_khuyenmai";
	}
}
