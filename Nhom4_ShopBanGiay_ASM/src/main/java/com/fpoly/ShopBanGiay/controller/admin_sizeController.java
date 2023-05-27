package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_sizeController {

	@GetMapping("/admin_size")
	public String admin_size() {
		return "/admin/admin_size";
	}
}
