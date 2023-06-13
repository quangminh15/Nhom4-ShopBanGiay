package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fpoly.ShopBanGiay.dao.ChiTietDonHangDAO;
import com.fpoly.ShopBanGiay.dao.DonHangDAO;
import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.ChiTietDonHang;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.SessionService;

@Controller
public class donhangController {
	@Autowired
	DonHangDAO dhDAO;
	
	@Autowired
	ChiTietDonHangDAO ctDAO;
	
	@Autowired
	SessionService session;
	@Autowired
	NguoiDungDAO nddao;
	@GetMapping("/donhang")
	public String donhang(Model model) {

		NguoiDung userSession = session.getSessionAttribute("user");
		int id = userSession.getMand();
		NguoiDung nguoidung = nddao.findById(id).get();
			List<DonHang> dh =dhDAO.findByNguoidung(nguoidung);
			model.addAttribute("orders",dh);
			return "/nguoidung/donhang";
			
			
		}
	@GetMapping("/donhang/chitietdonhang/{madh}")
	public String Chitet(Model model,@PathVariable("madh") Integer id) {

		List<ChiTietDonHang>  orderDetails = ctDAO.findByMaDH(id);
		Optional<DonHang> dh = dhDAO.findById(id);
		model.addAttribute("orderdetails", orderDetails);
		
		model.addAttribute("name",dh.get().getNguoinhan());
		model.addAttribute("idOrder",dh.get().getMadh());
		model.addAttribute("receiver",dh.get().getNguoinhan());
		model.addAttribute("phone",dh.get().getSdtnhanhang());
		model.addAttribute("address",dh.get().getDiachigiaohang());
		model.addAttribute("total",dh.get().getTongtien());
		return "/nguoidung/receipt";
		}
		
	
}
