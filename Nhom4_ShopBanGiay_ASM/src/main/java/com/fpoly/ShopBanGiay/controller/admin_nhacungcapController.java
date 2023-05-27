package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_nhacungcapController {

	@GetMapping("/admin_nhacungcap")
	public String admin_nhacungcap() {
		return "/admin/admin_nhacungcap";
	}
}
