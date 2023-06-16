package com.fpoly.ShopBanGiay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.service.SessionService;

@Controller
public class ThongTInController {
	@Autowired
	SessionService session;
	@GetMapping("/thongtin")
	public String ThongTin(Model model) {
		model.addAttribute("user", session.getSessionAttribute("user"));
		return "/nguoidung/thongtin";
	}
}
