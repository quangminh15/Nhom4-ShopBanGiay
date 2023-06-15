package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Controller
public class admin_sizeController {
	@Autowired
	SessionService session;

	@Autowired
	SizeDAO sizeDAO;

	@GetMapping("/admin/admin_size")
	public String admin_size(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field, @RequestParam("min") Optional<Double> min,
			@RequestParam("max") Optional<Double> max) {
		Size size = new Size();
		model.addAttribute("size", size);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("masize")).ascending());
		var list = sizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", list);

		return "/admin/admin_size";
	}

	@GetMapping("/admin/admin_size/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field, @RequestParam("min") Optional<Double> min,
			@RequestParam("max") Optional<Double> max) {
		return this.admin_size(model, p, field, min, max);
	}

	@RequestMapping("/admin/admin_size/create")
	public String add(@Valid @ModelAttribute("size") Size size, BindingResult result, Model model,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<Size> sizes = sizeDAO.findAll();
			model.addAttribute("sizes", sizes);
			return "/admin/admin_size";
		}

		try {
			sizeDAO.save(size);
			model.addAttribute("success", "Thêm thành công!");
		} catch (Exception e) {
			model.addAttribute("error", "Thêm thất bại!");
		} 
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masize").ascending());
		var list = sizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", list);
		size = new Size();
		model.addAttribute("size", size);

		return "/admin/admin_size";
	}

	@RequestMapping("/admin/admin_size/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masize").ascending());
		var list = sizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", list);
		Size size = sizeDAO.findById(id).orElse(null);
		model.addAttribute("size", size);
		return "/admin/admin_size";
	}

	@RequestMapping("/admin/admin_size/delete/{masize}")
	public String remove(Model model, @PathVariable("masize") Integer id, @RequestParam("p") Optional<Integer> p) {
		try {
			sizeDAO.deleteById(id);
			model.addAttribute("success", "Xóa thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Xóa thất bại: " + e);
			model.addAttribute("error", "Xóa thất bại!");
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masize").ascending());
		var list = sizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", list);
		Size size = new Size();
		model.addAttribute("size", size);
		return "/admin/admin_size";
	}

	@RequestMapping("/admin/admin_size/update")
	public String update(Model model, @Valid @ModelAttribute("size") Size size, BindingResult result,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<Size> sizes = sizeDAO.findAll();
			model.addAttribute("sizes", sizes);
			return "/admin/admin_size";
		}

		try {
			sizeDAO.save(size);
			model.addAttribute("success", "Cập nhật thành công!");
		} catch (Exception e) {
			model.addAttribute("error", "Cập nhật thất bại!");
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masize").ascending());
		var list = sizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", list);
		size = new Size();
		model.addAttribute("size", size);
		return "/admin/admin_size";
	}

	@RequestMapping("/admin/admin_size/clear")
	public String update(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masize").ascending());
		var list = sizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", list);
		Size size = new Size();
		model.addAttribute("size", size);
		return "/admin/admin_size";
	}

	@RequestMapping("/admin/admin_size/timkiemgia")
	public String getsizegiay(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("min") Optional<Double> min, @RequestParam("max") Optional<Double> max) {
		Size size = new Size();
		model.addAttribute("size", size);

		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);
		session.setSessionAttribute("min", minPrice);
		session.setSessionAttribute("max", maxPrice);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masize").ascending());
		Page<Size> sizes = sizeDAO.findAllBysizegiayBetween(minPrice, maxPrice, pageable);
		if (sizes.isEmpty()) {
			model.addAttribute("message", "Không có size mà bạn muốn tìm kiếm");
			sizes = sizeDAO.findAll(pageable);
		}
		var numberOfPages = sizes.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sizes", sizes);

		return "/admin/admin_size";
	}
}
