package com.fpoly.ShopBanGiay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.dao.YeuThichDAO;

@Controller
public class YeuThichController {
	@Autowired
	YeuThichDAO yeuthichDao;
	
	@GetMapping("/yeuthich")
	public String DangNhap(Model model) {
//		model.addAttribute("YT", yeuthichDao.findAll());
		return "/nguoidung/yeuthich";
	}
}
