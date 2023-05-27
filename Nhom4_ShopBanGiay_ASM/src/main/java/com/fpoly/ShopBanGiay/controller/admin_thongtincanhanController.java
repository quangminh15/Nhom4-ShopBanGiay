package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_thongtincanhanController {

	@GetMapping("/admin_thongtincanhan")
	public String admin_thongtincanhan() {
		return "/admin/admin_thongtincanhan";
	}
}
