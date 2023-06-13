package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.DanhMucDAO;
import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.SanPhamSize;

import com.fpoly.ShopBanGiay.service.SessionService;

@Controller
public class SanPhamController {
	@Autowired
	SessionService session;

	@Autowired
	SanPhamDAO sanphamDAO;

	@Autowired
	SanPhamSizeDAO spsizeDAO;

	@Autowired
	DanhMucDAO danhmucDAO;

	@Autowired
	GioHangDAO giohangDAO;

	@RequestMapping("/trangchu")
	public String DangNhap(SanPham sanpham, Model model) {
		sanpham = new SanPham();
		model.addAttribute("sanpham", sanpham);
		List<SanPham> sanphams = sanphamDAO.findAll();
		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/trangchu";
	}

	@RequestMapping("/sanpham")
	public String SanPhamsp(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("keywords") Optional<String> kw) {
		SanPham sanpham = new SanPham();
		model.addAttribute("sanpham", sanpham);

		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		Page<SanPham> sanphams = sanphamDAO.findAll(pageable);

		var numberOfPages = sanphams.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}

	@GetMapping("/sanpham/page")
	public String pagesp(SanPham sanpham, Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("keywords") Optional<String> kw) {
		return this.SanPhamsp(model, p, kw);
	}

	@RequestMapping("/sanpham/loai/{loai}")
	public String SanPhama(Model model, @RequestParam("p") Optional<Integer> p, @PathVariable("loai") Boolean loai) {
		SanPham sanpham = new SanPham();
		model.addAttribute("sanpham", sanpham);

		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		Page<SanPham> sanphams = sanphamDAO.findAllByLoai(loai, pageable);

		var numberOfPages = sanphams.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}

	@RequestMapping("/sanpham/dm/{madm}")
	public String SortDanhMuc(Model model, @PathVariable("madm") Integer madm, @RequestParam("p") Optional<Integer> p) {
		SanPham sanpham = sanphamDAO.findById(madm).orElse(null);
		model.addAttribute("sanpham", sanpham);

		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<SanPham> sanphams = sanphamDAO.findAllBytendm(madm, pageable);
		var numberOfPages = sanphams.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}

	@RequestMapping("/sanpham/chitietsp/{masp}")
	public String chitietspa(Model model, @PathVariable("masp") Integer masp, @Param("masps") Integer masps,
			@RequestParam("keywords") Optional<String> kw) {
		SanPham sanpham = sanphamDAO.findById(masp).orElse(null);
		model.addAttribute("sanpham", sanpham);

		String kwords = kw.orElse(sanpham.getDanhmuc().getTendm());
		session.getSessionAttribute("keywords");
		session.setSessionAttribute("keywords", kwords);
		List<SanPham> sanphams = sanphamDAO.findByTenDM("%" + kwords + "%");
		model.addAttribute("sanphams", sanphams);

		List<SanPhamSize> spsizes = spsizeDAO.findByMaSP(masp);
		model.addAttribute("sizes", spsizes);

		
		return "/nguoidung/chitietsp";
	}
	
	@RequestMapping("/sanpham/timkiem")
	public String getTimKiem(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("keywords") Optional<String> kw) {
		String kwords = kw.orElse("");
		session.getSessionAttribute("keywords");
		session.setSessionAttribute("keywords", kwords);

		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		Page<SanPham> sanphams = sanphamDAO.findAllBytenspLike("%" + kwords + "%", pageable);

		var numberOfPages = sanphams.getTotalPages();
		

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}

	@RequestMapping("/sanpham/timkiemgia")
	public String getProduct(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("min") Optional<Double> min, @RequestParam("max") Optional<Double> max) {
		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);
		session.setSessionAttribute("min", minPrice);
		session.setSessionAttribute("max", maxPrice);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("masp").ascending());
		Page<SanPham> sanphams = sanphamDAO.findBygiaBetween(minPrice, maxPrice, pageable);

		var numberOfPages = sanphams.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}

	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMucsp() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}

}
