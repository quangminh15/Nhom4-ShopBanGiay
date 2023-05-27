package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class thaydoithongtinController {

	@GetMapping("/thaydoithongtin")
	public String thaydoithongtin() {
		return "/nguoidung/thaydoithongtin";
	}
}
