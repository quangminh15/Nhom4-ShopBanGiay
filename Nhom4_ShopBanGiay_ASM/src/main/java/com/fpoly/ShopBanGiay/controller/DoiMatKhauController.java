package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoiMatKhauController {

	@GetMapping("/doimatkhau")
	public String doimatkhau() {
		return "doimatkhau";
	}
}
