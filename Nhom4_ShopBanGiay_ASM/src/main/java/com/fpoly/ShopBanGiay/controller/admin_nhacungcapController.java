package com.fpoly.ShopBanGiay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.dao.NhaCungCapDAO;

@Controller
public class admin_nhacungcapController {
	@Autowired
	NhaCungCapDAO nhacungcapDAO;
	
	@GetMapping("/admin_nhacungcap")
	public String admin_nhacungcap(Model model) {
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		return "/admin/admin_nhacungcap";
	}
}
