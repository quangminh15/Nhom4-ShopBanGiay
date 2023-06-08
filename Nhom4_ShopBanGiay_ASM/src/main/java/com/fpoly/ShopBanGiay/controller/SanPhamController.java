package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.DanhMucDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;

import jakarta.validation.Valid;

@Controller
public class SanPhamController {
	
	@Autowired
	SanPhamDAO sanphamDAO;
	
	@Autowired
	DanhMucDAO danhmucDAO;
	
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
		return "/nguoidung/chitietsp";
	}
	
	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMucsp() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}
	
}
