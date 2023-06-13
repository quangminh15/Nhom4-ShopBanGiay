package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.dao.DonHangDAO;
import com.fpoly.ShopBanGiay.model.DonHang;


@Controller
public class admin_donhangController {
	@Autowired 
	DonHangDAO donHangDao;
	
	@GetMapping("/admin/admin_donhang")
	public String admin_donhang(Model model) {
		List<DonHang> dh =donHangDao.findAll();
		model.addAttribute("orders",dh);
		return "/admin/admin_donhang";
	}
}
