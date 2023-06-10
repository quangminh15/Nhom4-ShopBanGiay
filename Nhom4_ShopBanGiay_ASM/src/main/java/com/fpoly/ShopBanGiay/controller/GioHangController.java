package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fpoly.ShopBanGiay.dao.GioHangDAO;


import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.Size;





@Controller
public class GioHangController {
	@Autowired
	GioHangDAO dao;
	

	@RequestMapping("/giohang")
	public String getGioHang(GioHang gh, Model model) {
		GioHang ctgh = new GioHang();
		model.addAttribute("cart",ctgh);
		List<GioHang> carts = dao.findGioHangByMaND(2);
		model.addAttribute("carts", carts);
		return "/nguoidung/giohang";
	}
//	@RequestMapping("/save")
//	public String update(@ModelAttribute("cart")GioHang item,Model model) {
//		
//		dao.save(item);
//		return "redirect:/giohang";
//	}
//	 @ResponseBody
//	    @PostMapping("/item/update")
//	    public GioHang updateCardItem(@RequestBody GioHang item) {
//	        // Process the data received via AJAX
//		 GioHang updatedItem = cart.update(item.getMagh(), item.getSoluong());
//	        return updatedItem;
//	    }
	
}
