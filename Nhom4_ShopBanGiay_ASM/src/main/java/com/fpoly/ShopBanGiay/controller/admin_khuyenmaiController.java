package com.fpoly.ShopBanGiay.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.KhuyenMaiDAO;
import com.fpoly.ShopBanGiay.model.GiamGia;

import jakarta.validation.Valid;

@Controller
public class admin_khuyenmaiController {
	@Autowired
	KhuyenMaiDAO khuyenmaiDao;
	
	@GetMapping("/admin_khuyenmai")
	public String admin_khuyenmai(Model model, @RequestParam("p") Optional<Integer> p) {
		GiamGia g = new GiamGia();
		model.addAttribute("KMS", g);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		var giamgia = khuyenmaiDao.findAll(pageable);
		var numberOfPages = giamgia.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("KM", giamgia);
		model.addAttribute("Action", "save_khuyenmai");
		return "/admin/admin_khuyenmai";
	}
	
	@GetMapping("/page")
	public String page(Model model, @RequestParam("p") Optional<Integer> p) {
		return this.admin_khuyenmai(model, p);
	}
	
	@PostMapping("/save_khuyenmai")
	public String save_khuyenmai(@Valid @ModelAttribute("KMS") GiamGia giamgia, Model model, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("KM", khuyenmaiDao.findAll());
			return "/admin/admin_khuyenmai";
		}
		khuyenmaiDao.save(giamgia);
//		model.addAttribute("KM", khuyenmaiDao.findAll());
		return "redirect:/admin_khuyenmai";
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
	
	@PostMapping("/admin_khuyenmai/clear")
	public String clear(@ModelAttribute("khuyenmai") GiamGia khuyenmai) {
		khuyenmai.setMagiamgia(null);
		khuyenmai.setTengiamgia(null);
		khuyenmai.setGiamgia(null);
		khuyenmai.setMota(null);
		khuyenmai.setNgayketthuc(null);
		khuyenmai.setNgaytao(null);
		return "redirect:/admin_khuyenmai";
	}
}
