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

import com.fpoly.ShopBanGiay.dao.NhaCungCapDAO;
import com.fpoly.ShopBanGiay.model.NhaCungCap;

@Controller
public class admin_nhacungcapController {
	@Autowired
	NhaCungCapDAO nhacungcapDAO;
	
	@GetMapping("/admin_nhacungcap")
	public String admin_nhacungcap(Model model) {
		NhaCungCap n = new NhaCungCap();
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		model.addAttribute("NCCS", n);
		model.addAttribute("Action", "save_nhacungcap");
		return "/admin/admin_nhacungcap";
	}
	
	@PostMapping("/save_nhacungcap")
	public String save_nhacungcap(Model model, @ModelAttribute("NCCS") NhaCungCap nhacungcap) {
		nhacungcapDAO.save(nhacungcap);
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		return "/admin/admin_nhacungcap";
	}
	
	@RequestMapping("/edit_nhacungcap/{mancc}")
	public String eidt_nhacungcap(Model model, @PathVariable(name="mancc") Integer mancc) {
		Optional<NhaCungCap> n = nhacungcapDAO.findById(mancc);
		if(n.isPresent()) {
			model.addAttribute("NCCS", n.get());
			model.addAttribute("NCC", nhacungcapDAO.findAll());
		}
		model.addAttribute("Action", "/save_nhacungcap");
		return "/admin/admin_nhacungcap";
	}
	
	@RequestMapping("/delete_nhacungcap/{mancc}")
	public String delete_nhacungcap(Model model, @PathVariable(name="mancc") Integer mancc) {
		NhaCungCap n = new NhaCungCap();
		nhacungcapDAO.deleteById(mancc);
		model.addAttribute("NCCS", n);
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		return "/admin/admin_nhacungcap";
	}
}
