package com.fpoly.ShopBanGiay.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NhaCungCapDAO;
import com.fpoly.ShopBanGiay.model.NhaCungCap;

import jakarta.validation.Valid;

@Controller
public class admin_nhacungcapController {
	@Autowired
	NhaCungCapDAO nhacungcapDAO;
	
	@GetMapping("/admin_nhacungcap")
	public String admin_nhacungcap(Model model, @RequestParam("p") Optional<Integer> p) {
		NhaCungCap n = new NhaCungCap();
		model.addAttribute("nccs", n);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		var nhacungcap = nhacungcapDAO.findAll(pageable);
		var numberOfPages = nhacungcap.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("NCC", nhacungcap);
		model.addAttribute("Action", "save_nhacungcap");
		return "/admin/admin_nhacungcap";
	}
	
	@GetMapping("/page1")
	public String page1(Model model, @RequestParam("p") Optional<Integer> p) {
		return this.admin_nhacungcap(model, p);
	}
	
	@PostMapping("/save_nhacungcap")
	public String save_nhacungcap(@Valid @ModelAttribute("nccs") NhaCungCap nhacungcap, Model model, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("NCC",nhacungcapDAO.findAll());
			return "/admin/admin_nhacungcap";
		}
		nhacungcapDAO.save(nhacungcap);
//		model.addAttribute("NCC", nhacungcapDAO.findAll());
		return "redirect:/admin_nhacungcap";
	}
	
	@RequestMapping("/edit_nhacungcap/{mancc}")
	public String eidt_nhacungcap(Model model, @PathVariable(name="mancc") Integer mancc) {
		Optional<NhaCungCap> n = nhacungcapDAO.findById(mancc);
		if(n.isPresent()) {
			model.addAttribute("nccs", n.get());
			model.addAttribute("NCC", nhacungcapDAO.findAll());
		}
		model.addAttribute("Action", "/save_nhacungcap");
		return "/admin/admin_nhacungcap";
	}
	
	@RequestMapping("/delete_nhacungcap/{mancc}")
	public String delete_nhacungcap(Model model, @PathVariable(name="mancc") Integer mancc) {
		NhaCungCap n = new NhaCungCap();
		nhacungcapDAO.deleteById(mancc);
		model.addAttribute("nccs", n);
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		model.addAttribute("Action", "/save_nhacungcap");
		return "redirect:/admin_nhacungcap";
	}
}
