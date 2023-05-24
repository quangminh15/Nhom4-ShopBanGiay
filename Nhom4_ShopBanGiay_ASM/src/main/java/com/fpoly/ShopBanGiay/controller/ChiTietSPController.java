package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChiTietSPController {

	@GetMapping("/chitietsp")
	public String ChiTietsp() {
		return "chitietsp";
	}
}
