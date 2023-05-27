package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class admin_sanphamsizeController {

	@GetMapping("/admin_sanphamsize")
	public String admin_sanphamsize() {
		return "/admin/admin_sanphamsize";
	}
}
