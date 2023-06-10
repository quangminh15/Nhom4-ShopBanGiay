package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

 

@Controller
public class errorController {
	
	@RequestMapping("/errorpage")
	public String getGioHang(Model model, @RequestParam("error") String error) {System.out.println("Lỗi: "+error);
		model.addAttribute("message", error);
		return "/nguoidung/404";
	}
}
