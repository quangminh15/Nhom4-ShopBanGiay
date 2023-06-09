package com.fpoly.ShopBanGiay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.dao.KhuyenMaiDAO;

@Controller
public class admin_khuyenmaiController {
	@Autowired
	KhuyenMaiDAO khuyenmaiDao;
	
	@GetMapping("/admin_khuyenmai")
	public String admin_khuyenmai(Model model) {
		model.addAttribute("KM", khuyenmaiDao.findAll());
		return "/admin/admin_khuyenmai";
	}
}
