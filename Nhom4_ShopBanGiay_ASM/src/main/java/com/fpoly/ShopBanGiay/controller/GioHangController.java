package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.SessionService;
import com.fpoly.ShopBanGiay.service.ShopingCartServiceImp;





@Controller
public class GioHangController {
	@Autowired
	GioHangDAO dao;
	
	@Autowired
	NguoiDungDAO nddao;
	
	@Autowired
	ShopingCartServiceImp cart;
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/giohang")
	public String getGioHang(GioHang gh, Model model) {
		GioHang ctgh = new GioHang();
		model.addAttribute("cart",ctgh);
		List<GioHang> carts = dao.findGioHangByMaND(2);
		model.addAttribute("carts", carts);
		return "/nguoidung/giohang";
	}
	
	@PostMapping("/addtocart")
	public String addToCart(@Param("masps")Integer masps,@Param("soluong")Integer soluong) {
		
		
		
		
		NguoiDung nguoidung = nddao.findById(4).get();
		
		cart.addToCart(masps, soluong, nguoidung);
		return "redirect:/giohang";
		}
	
	
}

	

