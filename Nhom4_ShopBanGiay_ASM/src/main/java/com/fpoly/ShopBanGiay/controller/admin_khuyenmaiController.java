package com.fpoly.ShopBanGiay.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.KhuyenMaiDAO;
import com.fpoly.ShopBanGiay.model.GiamGia;

@Controller
public class admin_khuyenmaiController {
	@Autowired
	KhuyenMaiDAO khuyenmaiDao;
	
	@GetMapping("/admin_khuyenmai")
	public String admin_khuyenmai(Model model) {
		GiamGia g = new GiamGia();
		model.addAttribute("KM", khuyenmaiDao.findAll());
		model.addAttribute("KMS", g);
		model.addAttribute("Action", "save_khuyenmai");
		return "/admin/admin_khuyenmai";
	}
	
	@PostMapping("/save_khuyenmai")
	public String save_khuyenmai(Model model, @ModelAttribute("KMS") GiamGia giamgia) {
		khuyenmaiDao.save(giamgia);
		model.addAttribute("KM", khuyenmaiDao.findAll());
		return "/admin/admin_khuyenmai";
	}
	
	@RequestMapping("/edit_khuyenmai/{magiamgia}")
	public String edit_khuyenmai(Model model, @PathVariable(name="magiamgia") Integer magiamgia) {
		Optional<GiamGia> g = khuyenmaiDao.findById(magiamgia);
		if(g.isPresent()) {
			model.addAttribute("KMS", g.get());
			model.addAttribute("KM", khuyenmaiDao.findAll());
		}
		
		model.addAttribute("Action", "/save_khuyenmai");
		return "/admin/admin_khuyenmai";
	}
	
	@RequestMapping("/delete_khuyenmai/{magiamgia}")
	public String delete_khuyenmai(Model model, @PathVariable(name="magiamgia") Integer magiamgia) {
		GiamGia g = new GiamGia();
		khuyenmaiDao.deleteById(magiamgia);
		model.addAttribute("KMS", g);
		model.addAttribute("KM", khuyenmaiDao.findAll());
		model.addAttribute("Action", "/save_khuyenmai");
		return "/admin/admin_khuyenmai";
	}
}
