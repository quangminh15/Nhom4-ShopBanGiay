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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.SizeDAO;
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class admin_sizeController {
	@Autowired
	SessionService session;

	@Autowired
	SizeDAO sizeDAO;

	@RequestMapping("/admin_size")
	public String admin_size(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		Size size = new Size();
		model.addAttribute("size", size);

		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("masize")).ascending());
		Page<Size> sizes = sizeDAO.findAll(pageable);

		var numberOfPages = sizes.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", sizes);
		return "/admin/admin_size";
	}

	@GetMapping("/admin_size/page")
	public String page(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		return this.admin_size(model, field, p);
	}

	@RequestMapping("/admin_size/edit/{masize}")
	public String editsize(Model model, @PathVariable("masize") Integer MaSize,
			@RequestParam("field") Optional<String> field, @RequestParam("p") Optional<Integer> p) {
		Size size = sizeDAO.findById(MaSize).get();
		model.addAttribute("size", size);

		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("masize")).ascending());
		Page<Size> sizes = sizeDAO.findAll(pageable);

		var numberOfPages = sizes.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("sizes", sizes);
		return "/admin/admin_size";
	}

	@PostMapping("/admin_size/create")
	public String createsize(@Valid @ModelAttribute("size") Size size, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Size> sizes = sizeDAO.findAll();
			model.addAttribute("sizes", sizes);
			return "/admin/admin_size";
		}
		sizeDAO.save(size);
		return "redirect:/admin_size";
	}

	@PostMapping("/admin_size/update")
	public String updatesize(@Valid @ModelAttribute("size") Size size, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Size> sizes = sizeDAO.findAll();
			model.addAttribute("sizes", sizes);
			return "/admin/admin_size";
		}
		sizeDAO.save(size);
		return "redirect:/admin_size/edit/" + size.getMasize();
	}

	@RequestMapping("/delete/{masize}")
	public String deletesize(@PathVariable("masize") Integer masize) {
		sizeDAO.deleteById(masize);
		return "redirect:/admin_size";
	}

	@PostMapping("/admin_size/clear")
	public String clear(@ModelAttribute("size") Size size) {
		size.setMasize(0);
		size.setSizegiay(null);
		size.setTrangthai(true);
		return "redirect:/admin_size";
	}
}
