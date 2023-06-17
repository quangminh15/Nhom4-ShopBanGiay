package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.ChiTietDonHangDAO;
import com.fpoly.ShopBanGiay.dao.DonHangDAO;
import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.ChiTietDonHang;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.SessionService;
import com.fpoly.ShopBanGiay.service.ShoppingCartService;

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
	
	@Autowired
	ShoppingCartService spService;
	@GetMapping("/donhang")
	public String donhang(Model model,@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {

		NguoiDung userSession = session.getSessionAttribute("user");
		int id = userSession.getMand();
		NguoiDung nguoidung = nddao.findById(id).get();
		
		Pageable page = PageRequest.of(p.orElse(0),5, Sort.by(Direction.DESC, field.orElse("ngaytao")).descending());
		
			
			Page<DonHang> dh =dhDAO.findByNguoidung(nguoidung,page);
			
			var numberOfPages = dh.getTotalPages();
			model.addAttribute("currIndex", p.orElse(0));
			model.addAttribute("numberOfPages", numberOfPages);
			
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
		
	@GetMapping("/donhang/cancel/{madh}")
	public String cancelOrder(@PathVariable("madh") Integer id) {
		
		spService.statusOrder(id,"Đã Hủy");
		
		return"redirect:/donhang";
		
	}
	
	@GetMapping("/donhang/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		return this.donhang(model, p, field);
	}
	
}
