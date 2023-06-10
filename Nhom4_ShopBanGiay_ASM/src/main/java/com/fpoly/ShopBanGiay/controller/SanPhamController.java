package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


import jakarta.validation.Valid;

@Controller
public class SanPhamController {
	
	@Autowired
	SanPhamDAO sanphamDAO;
	
	@Autowired
	SanPhamSizeDAO spsizeDAO;
	
	@Autowired
	DanhMucDAO danhmucDAO;
	
	@Autowired
	GioHangDAO giohangDAO;

	
	@RequestMapping("/sanpham")
	public String SanPham(SanPham sanpham, Model model) {
		sanpham = new SanPham();
		model.addAttribute("sanpham",sanpham);
		List<SanPham> sanphams = sanphamDAO.findAll();
		model.addAttribute("sanphams", sanphams);
		return "/nguoidung/sanpham";
	}
	
	@RequestMapping("/sanpham/chitietsp/{masp}")
	public String chitietspa(Model model, @PathVariable("masp") Integer MaSP) {
		SanPham sanpham = sanphamDAO.findById(MaSP).get();
		model.addAttribute("sanpham",sanpham);
		
		List<SanPham> sanphams = sanphamDAO.findAll();
		model.addAttribute("sanphams", sanphams);
		
		List<SanPhamSize>spsizes = spsizeDAO.findByMaSP(MaSP);
		model.addAttribute("sizes", spsizes);
		return "/nguoidung/chitietsp";
	}
	
	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMucsp() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}
	
	@RequestMapping("/addtogiohang")
	public String themGioHang(@ModelAttribute("giohang") GioHang gh,@Param("mand")Integer mand,@Param("masps")Integer masps,@Param("soluong")Integer soluong,Model model) {
		
		giohangDAO.save(gh);
		
		return "/nguoidung/chitietsp";
	}
}
