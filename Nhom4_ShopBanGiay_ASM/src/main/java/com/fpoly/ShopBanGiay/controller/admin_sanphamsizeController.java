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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.dao.SizeDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class admin_sanphamsizeController {
	@Autowired
	SessionService session;

	@Autowired
	SanPhamSizeDAO sanphamsizeDAO;
	
	@Autowired
	SanPhamDAO sanphamDAO;
	
	@Autowired
	SizeDAO sizeDAO;
	
	@GetMapping("/admin_sanphamsize")
	public String admin_sanphamsize(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		SanPhamSize sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize",sanphamsize);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("masps")).ascending());
		Page<SanPhamSize> sanphamsizes = sanphamsizeDAO.findAll(pageable);

		var numberOfPages = sanphamsizes.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", sanphamsizes);
		
		return "admin/admin_sanphamsize";
	}
	
	@GetMapping("/admin_sanphamsize/page")
	public String page(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		return this.admin_sanphamsize(model, field, p);
	}
	
	@ModelAttribute("sanphams")
	public List<SanPham> getsanpham() {
		List<SanPham> sanpham = sanphamDAO.findAll();
		return sanpham;
	}
	
	@ModelAttribute("sizes")
	public List<Size> getsizegiayy() {
		List<Size> sizee = sizeDAO.findAll();
		return sizee;
	}
	
	@PostMapping("/admin_sanphamsize")
	public String validSize(@Valid @ModelAttribute("sanphamsize") SanPhamSize sanphamsize, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "admin/admin_sanphamsize";
		}
		model.addAttribute("sanphamsize",sanphamsize);
		return "admin/admin_sanphamsize";
	}
}
