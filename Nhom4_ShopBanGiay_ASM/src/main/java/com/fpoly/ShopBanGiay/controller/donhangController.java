package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class donhangController {

	@GetMapping("/donhang")
	public String donhang() {
		return "/nguoidung/donhang";
	}
}
