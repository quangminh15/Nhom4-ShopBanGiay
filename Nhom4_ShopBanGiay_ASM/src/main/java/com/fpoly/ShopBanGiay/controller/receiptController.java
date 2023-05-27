package com.fpoly.ShopBanGiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class receiptController {

	@GetMapping("/receipt")
	public String receipt() {
		return "/nguoidung/receipt";
	}
}
