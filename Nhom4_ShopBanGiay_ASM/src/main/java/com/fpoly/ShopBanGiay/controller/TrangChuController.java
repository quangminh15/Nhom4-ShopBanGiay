package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.DanhMucDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;

@Controller
public class TrangChuController {
	@Autowired
	SanPhamDAO sanphamDAO;
	
	@Autowired
	DanhMucDAO danhmucDAO;
	
//	@RequestMapping("/trangchu")
//	public String DangNhap(SanPham sanpham, Model model) {
//		sanpham = new SanPham();
//		model.addAttribute("sanpham",sanpham);
//		List<SanPham> sanphams = sanphamDAO.findAll();
//		model.addAttribute("sanphams", sanphams);
//		return "/nguoidung/trangchu";
//	}
	
	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMucsp() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}
}
