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
import com.fpoly.ShopBanGiay.model.ChiTietDonHang;
import com.fpoly.ShopBanGiay.model.ChiTietGioHang;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.Size;


@Controller
public class GioHangController {
	@Autowired
	ChiTietGioHangDAO dao;
	@RequestMapping("/giohang")
	public String getGioHang(ChiTietGioHang gh, Model model) {
		ChiTietGioHang ctgh = new ChiTietGioHang();
		model.addAttribute("cart",ctgh);
		List<ChiTietGioHang> carts = dao.findAll();
		model.addAttribute("carts", carts);
		return "/nguoidung/giohang";
	}
	
	
}
