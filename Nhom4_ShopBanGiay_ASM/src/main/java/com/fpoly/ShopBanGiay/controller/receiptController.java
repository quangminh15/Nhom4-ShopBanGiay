package com.fpoly.ShopBanGiay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.service.SessionService;

@Controller
public class receiptController {
	
	@Autowired 
	SessionService session;
	@GetMapping("/receipt")
	public String receipt(Model model) {

		// NiHuynh
		model.addAttribute("user", session.getSessionAttribute("user"));
		// *******
		return "/nguoidung/receipt";
	}
}
