package com.fpoly.ShopBanGiay.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.ShopBanGiay.dao.DanhMucDAO;
import com.fpoly.ShopBanGiay.dao.KhuyenMaiDAO;
import com.fpoly.ShopBanGiay.dao.NhaCungCapDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.GiamGia;
import com.fpoly.ShopBanGiay.model.NhaCungCap;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.YeuThich;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class admin_sanphamController {
	@Autowired
	HttpServletRequest request;

	@Autowired
	SanPhamDAO sanphamDAO;

	@Autowired
	DanhMucDAO danhmucDAO;

	@Autowired
	NhaCungCapDAO nhacungcapDAO;

	@Autowired
	KhuyenMaiDAO giamgiaDAO;

	@GetMapping("/admin_sanpham")
	public String admin_danhmucsp(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		SanPham sanpham = new SanPham();
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		model.addAttribute("sanpham", sanpham);

		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("masp")).ascending());
		var list = sanphamDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", list);

		return "/admin/admin_sanpham";
	}

	@ModelAttribute("danhmucs")
	public List<DanhMuc> getAllDanhMuc() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}

	@ModelAttribute("nhacungcaps")
	public List<NhaCungCap> getAllNCC() {
		return nhacungcapDAO.findAll();
	}

	@ModelAttribute("giamgias")
	public List<GiamGia> getAllGiamGia() {
		return giamgiaDAO.findAll();
	}

	@GetMapping("/admin_sanpham/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		return this.admin_danhmucsp(model, p, field);
	}

	@RequestMapping("/admin_sanpham/create")
	public String add(@Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult result, Model model,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<SanPham> sanphams = sanphamDAO.findAll();
			model.addAttribute("sanphams", sanphams);
			return "/admin/admin_sanpham";
		}

		sanphamDAO.save(sanpham);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		var list = sanphamDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", list);
		sanpham = new SanPham();
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		model.addAttribute("sanpham", sanpham);

		return "/admin/admin_sanpham";
	}

	@RequestMapping("/admin_sanpham/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		var list = sanphamDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", list);
		SanPham sanpham = sanphamDAO.findById(id).orElse(null);
//		sanpham.getHinhanh1();
//		sanpham.getHinhanh2();
//		sanpham.getHinhanh3();
//		sanpham.getDanhmuc().getMadm();
//		sanpham.getNhacungcap().getMancc();
//		sanpham.getGiamgia().getMagiamgia();
		model.addAttribute("sanpham", sanpham);
		return "/admin/admin_sanpham";
	}

	@RequestMapping("/admin_sanpham/delete/{masp}")
	public String remove(Model model, @PathVariable("masp") Integer id, @RequestParam("p") Optional<Integer> p) {
		sanphamDAO.deleteById(id);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		var list = sanphamDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", list);
		SanPham sanpham = new SanPham();
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		model.addAttribute("sanpham", sanpham);
		return "/admin/admin_sanpham";
	}

	@RequestMapping("/admin_sanpham/update")
	public String update(Model model, @Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult result,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<SanPham> sanphams = sanphamDAO.findAll();
			model.addAttribute("sanphams", sanphams);
			return "/admin/admin_sanpham";
		}

		sanphamDAO.save(sanpham);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		var list = sanphamDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", list);
		sanpham = new SanPham();
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		model.addAttribute("sanpham", sanpham);
		return "/admin/admin_sanpham";
	}

	@RequestMapping("/admin_sanpham/clear")
	public String update(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		var list = sanphamDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", list);
		SanPham sanpham = new SanPham();
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		model.addAttribute("sanpham", sanpham);
		return "/admin/admin_sanpham";
	}
}
