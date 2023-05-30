package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_nguoidungController {

	@GetMapping("/admin_nguoidung")
	public String admin_nguoidung() {
		return "/admin/admin_nguoidung";
	}
}
