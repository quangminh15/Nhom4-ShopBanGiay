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

import com.fpoly.ShopBanGiay.dao.DonHangDAO;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.service.ShoppingCartService;


@Controller
public class admin_donhangController {
	@Autowired 
	DonHangDAO donHangDao;
	@Autowired
	ShoppingCartService spService;
	@GetMapping("/admin/admin_donhang")
	public String admin_donhang(Model model,@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		
		Pageable page = PageRequest.of(p.orElse(0),5, Sort.by(Direction.DESC, field.orElse("ngaytao")).descending());
		
		Page<DonHang> dh =donHangDao.findByTrangthai("Đang Chờ Xác Nhận",page);
		
		var numberOfPages = dh.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		
		model.addAttribute("orders",dh);
		return "/admin/admin_donhang"; 
	}
	@GetMapping("/admin/admin_donhangdaxacnhan")
	public String admin_donhangdaXN(Model model,@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		Pageable page = PageRequest.of(p.orElse(0),5, Sort.by(Direction.DESC, field.orElse("ngaytao")).descending());
		Page<DonHang> dh =donHangDao.findByTrangthai("Đã Xác Nhận",page);
		
		var numberOfPages = dh.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("orders",dh);
		return "/admin/admin_donhang"; 
	}
	
	@GetMapping("/admin/admin_acceptorder/{madh}")
	public String acceptOrder(@PathVariable("madh") Integer id) {
		
		spService.statusOrder(id,"Đã Xác Nhận");
		
		return"redirect:/admin/admin_donhangdaxacnhan";
		
	}
	
	@GetMapping("/admin/admin_donhang/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		return this.admin_donhang(model, p, field);
	}
}
