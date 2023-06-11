package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	

	@RequestMapping("/sanpham")
	public String SanPhamsp(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("temdm") Optional<String> kw) {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		model.addAttribute("danhmucs",danhmuc);
		
		SanPham sanpham = new SanPham();
		model.addAttribute("sanpham", sanpham);

		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		Page<SanPham> sanphams = sanphamDAO.findAll(pageable);

		var numberOfPages = sanphams.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}

	@GetMapping("/sanpham/page")
	public String pagesp(SanPham sanpham, Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("tendm") Optional<String> kw) {
		return this.SanPhamsp(model, p, kw);
	}

	@GetMapping("/sanpham/dm")
	public String SortDanhMuc(SanPham sanpham, Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("temdm") Optional<String> kw) {	
		sanpham = new SanPham();
		model.addAttribute("sanpham", sanpham);
		
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		model.addAttribute("danhmucs",danhmuc);
		DanhMuc danhmuc1 = new DanhMuc();
		danhmuc1.getTendm();
		String kwords = danhmuc1.getTendm();
		session.getSessionAttribute("tendm");
		session.setSessionAttribute("tendm", kwords);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<SanPham> sanphams = sanphamDAO.findByDanhmuc(danhmuc1, pageable);

		var numberOfPages = sanphams.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("sanphams", sanphams);
		
		return "/nguoidung/sanpham";
	}

	@RequestMapping("/sanpham/chitietsp/{masp}")
	public String chitietspa(Model model, @PathVariable("masp") Integer masp,@Param("masps")Integer masps) {
		SanPham sanpham = sanphamDAO.findById(masp).orElse(null);

		model.addAttribute("sanpham", sanpham);
		
		
		List<SanPham> sanphams = sanphamDAO.findAll();
		model.addAttribute("sanphams", sanphams);
		
		List<SanPhamSize>spsizes = spsizeDAO.findByMaSP(masp);
		model.addAttribute("sizes", spsizes);
		
		
		
		return "/nguoidung/chitietsp";
	}
	
	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMucsp() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}
	
	

//	@ModelAttribute("danhmucs")
//	public List<DanhMuc> getDanhMucsp() {
//		
//		return danhmuc;
//	}

}
