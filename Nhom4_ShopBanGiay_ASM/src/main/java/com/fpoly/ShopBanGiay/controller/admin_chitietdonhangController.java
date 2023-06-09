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
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.model.ChiTietDonHang;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.service.SessionService;




@Controller
public class admin_chitietdonhangController {
	@Autowired 
	ChiTietDonHangDAO chiTietDHDao;
	@Autowired 
	DonHangDAO dhDao;
	@Autowired 
	SanPhamSizeDAO spsDao;
	
	@Autowired
	SessionService session;
	
	@GetMapping("/admin_chitietdonhang/{id}")
	public String admin_chitietdonhang(Model model, @PathVariable("id") Integer id) {

		List<SanPhamSize>  orderDetails = spsDao.findByMaDH(id);
		Optional<DonHang> dh = dhDao.findById(id);
		model.addAttribute("orderdetails", orderDetails);
		
		model.addAttribute("madh",dh.get().getMadh());
		model.addAttribute("nguoinhan",dh.get().getNguoinhan());
		model.addAttribute("sdt",dh.get().getSdtnhanhang());
		model.addAttribute("diachi",dh.get().getDiachigiaohang());
		model.addAttribute("tongtien",dh.get().getTongtien());
		
		return "/admin/admin_chitietdonhang";
	}
}
