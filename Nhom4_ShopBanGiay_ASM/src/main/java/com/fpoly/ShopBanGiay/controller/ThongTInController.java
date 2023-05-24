package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThongTInController {

	@GetMapping("/thongtin")
	public String ThongTin() {
		return "thongtin";
	}
}
