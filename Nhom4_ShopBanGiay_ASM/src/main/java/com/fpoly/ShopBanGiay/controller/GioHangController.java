package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.ChiTietGioHangDAO;
import com.fpoly.ShopBanGiay.model.SanPhamSize;


@Controller
public class GioHangController {
	@Autowired
	ChiTietGioHangDAO dao;
	@GetMapping("/giohang")
	public String GioHang(Model model) {
		SanPhamSize item = new SanPhamSize();
		model.addAttribute("carts", item);
		
	
		List<SanPhamSize> products = dao.getGioHang();

		
		model.addAttribute("carts", products);
		
		return "/nguoidung/giohang";
	}
	
	
}
