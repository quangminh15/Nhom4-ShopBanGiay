package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.SessionService;
import com.fpoly.ShopBanGiay.service.ShoppingCartService;

@Controller
public class thongtindonhangController {
	@Autowired
	GioHangDAO dao;
	
	@Autowired
	NguoiDungDAO nddao;
	
	@Autowired
	ShoppingCartService cart;
	@Autowired
	SessionService sessionService;
	@GetMapping("/thongtindonhang")
	public String thongtindonhang(GioHang gh, Model model) {
		NguoiDung userSession = sessionService.getSessionAttribute("user");
		int id = userSession.getMand();
		NguoiDung user = nddao.findById(id).get();
		model.addAttribute("user", user);
		
		
		List<GioHang> items = dao.findGioHangByMaND(id);
			
		double total = 0.0;
		for (GioHang gioHang : items) {
			total = total + gioHang.getSubtotal();
		}
		model.addAttribute("items", items);
		model.addAttribute("total",total);
		
		return "/nguoidung/thongtindonhang";
	}
	
	@RequestMapping("/giohang/thanhtoan")
	public String thanhToan(@RequestParam("nguoinhan")String ten,@RequestParam("email")String email,@RequestParam("sdt")String sdt,@RequestParam("diachi")String diachi) {
		
		NguoiDung userSession = sessionService.getSessionAttribute("user");
		int id = userSession.getMand();	
		NguoiDung nguoidung = nddao.findById(id).get();
		
		List<GioHang> items = dao.findGioHangByMaND(id);
		
		double total = 0.0;
		for (GioHang gioHang : items) {
			total = total + gioHang.getSubtotal();
		}
		
		DonHang order =  cart.addOrder(nguoidung, diachi, ten, sdt, total);
		
		cart.addCartItemsToOderDetails(nguoidung,order);
		
		cart.removeAll(id);
		
		return "/nguoidung/success";
	}

}
