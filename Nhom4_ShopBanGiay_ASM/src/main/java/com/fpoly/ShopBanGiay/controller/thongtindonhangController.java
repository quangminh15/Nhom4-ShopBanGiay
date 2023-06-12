package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.model.GioHang;

@Controller
public class thongtindonhangController {
	@Autowired
	GioHangDAO dao;
	@GetMapping("/thongtindonhang")
	public String thongtindonhang(GioHang gh, Model model) {
		
		List<GioHang> items = dao.findGioHangByMaND(4);
		model.addAttribute("items", items);
		
		return "/nguoidung/thongtindonhang";
	}
}
