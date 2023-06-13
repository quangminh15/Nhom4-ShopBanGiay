package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
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
	SanPhamSizeDAO spsdao;
	
	@Autowired
	ShopingCartServiceImp cart;
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/giohang")
	public String getGioHang(GioHang gh, Model model) {
		NguoiDung userSession = session.getSessionAttribute("user");
		int id = userSession.getMand();
		GioHang ctgh = new GioHang();
		model.addAttribute("cart",ctgh);
		
		List<GioHang> carts = dao.findGioHangByMaND(id);
		model.addAttribute("carts", carts);
		
//		int tongTien =  dao.tongTien(4);
//		model.addAttribute("totalAmount",tongTien);
//		System.out.println(tongTien);
		
		return "/nguoidung/giohang";
		
	}
	
	@RequestMapping("/giohang/xoa/{id}")
	public String xoa(GioHang gh, Model model, @PathVariable("id")Integer id ) {
		
		cart.remove(id);
		
		return "redirect:/giohang";
		
	}
	
	
	@PostMapping("/addtocart")
	public String addToCart(@Param("masps")Integer masps,@Param("soluong")Integer soluong) {
		
		NguoiDung userSession = session.getSessionAttribute("user");
		int id = userSession.getMand();
		NguoiDung nguoidung = nddao.findById(id).get();
		
		Integer qty = cart.addToCart(masps, soluong, nguoidung);
		return "redirect:/giohang";
		}
	
	@ResponseBody
	@PostMapping("/updateqty/{id}/{qty}")
	public String updateQty(@PathVariable("id")Integer masps,@PathVariable("qty")Integer soluong) {
		NguoiDung userSession = session.getSessionAttribute("user");
		int id = userSession.getMand();
		
		NguoiDung nguoidung = nddao.findById(id).get();
		
		double subtotal = cart.updateQuty(masps, soluong, nguoidung);
		return String.valueOf(subtotal);
		}
	
	
}

	

