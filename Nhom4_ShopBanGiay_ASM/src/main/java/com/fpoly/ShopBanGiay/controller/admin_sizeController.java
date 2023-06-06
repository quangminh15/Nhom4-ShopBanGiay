package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.SizeDAO;
import com.fpoly.ShopBanGiay.model.Size;

import jakarta.validation.Valid;

@Controller
public class admin_sizeController {

	@Autowired
	SizeDAO sizeDAO;
	
	@RequestMapping("/admin_size")
	public String admin_size(Size size, Model model) {
		size = new Size();
		model.addAttribute("size",size);
		List<Size> sizes = sizeDAO.findAll();
		model.addAttribute("sizes", sizes);
		return "/admin/admin_size";
	}
	
	@RequestMapping("/edit/{masize}")
	public String edit(Model model, @PathVariable("masize") Integer MaSize) {
		Size size = sizeDAO.findById(MaSize).get();
		model.addAttribute("size",size);
		List<Size> sizes = sizeDAO.findAll();
		model.addAttribute("sizes", sizes);
		return "/admin/admin_size";
	}
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("size") Size size, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Size> sizes = sizeDAO.findAll();
			model.addAttribute("sizes", sizes);
			return "/admin/admin_size";
		}
		sizeDAO.save(size);
		return "redirect:/admin/admin_size";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("size") Size size, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Size> sizes = sizeDAO.findAll();
			model.addAttribute("sizes", sizes);
			return "/admin/admin_size";
		}
		sizeDAO.save(size);
		return "redirect:/edit/" + size.getMasize();
	}
	
	@RequestMapping("/delete/{MaSize}")
	public String create(@PathVariable("masize") Integer MaSize) {
		sizeDAO.deleteById(MaSize);
		return "redirect:/admin/admin_size";
	}
	
	@PostMapping("/clear")
	public String clear(@ModelAttribute("size") Size size) {
		size.setMasize(0);
		size.setSize(null);
		size.setTrangthai(true);
		return "redirect:/admin/admin_size";
	}
}
