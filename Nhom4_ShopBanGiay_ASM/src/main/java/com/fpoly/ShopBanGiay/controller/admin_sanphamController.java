package com.fpoly.ShopBanGiay.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.ShopBanGiay.dao.DanhMucDAO;
import com.fpoly.ShopBanGiay.dao.NhaCungCapDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.NhaCungCap;
import com.fpoly.ShopBanGiay.model.SanPham;

import jakarta.validation.Valid;

@Controller
public class admin_sanphamController {

	@Autowired
	SanPhamDAO sanphamDAO;
	
	@Autowired
	DanhMucDAO danhmucDAO;
	
	@Autowired
	NhaCungCapDAO nhacungcapDAO;
	
	
	
	@GetMapping("/admin_sanpham")
	public String admin_sanpham(SanPham sanpham, Model model) {
		sanpham = new SanPham();
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		model.addAttribute("sanpham",sanpham);
		List<SanPham> sanphams = sanphamDAO.findAll();
		model.addAttribute("sanphams", sanphams);
		return "/admin/admin_sanpham";
	}
	
	@ModelAttribute("danhmucs")
	public List<DanhMuc> getDanhMucsp() {
		List<DanhMuc> danhmuc = danhmucDAO.findAll();
		return danhmuc;
	}
	
	@ModelAttribute("nhacungcap")
	public List<NhaCungCap> getNhaCungCapsp() {
		List<NhaCungCap> nhacungcap = nhacungcapDAO.findAll();
		return nhacungcap;
	}
	
	@RequestMapping("/admin_sanpham/edit/{masp}")
	public String edit(Model model, @PathVariable("masp") Integer MaSP) {
		SanPham sanpham = sanphamDAO.findById(MaSP).get();
		
		model.addAttribute("sanpham",sanpham);
		List<SanPham> sanphams = sanphamDAO.findAll();
		
		model.addAttribute("sanphams", sanphams);
		return "/admin/admin_sanpham";
	}
	
	@PostMapping("/admin_sanpham/create")
	public String create1(@Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<SanPham> sanphams = sanphamDAO.findAll();
			model.addAttribute("sanphams", sanphams);
			return "/admin/admin_sanpham";
		}
		
		
		sanphamDAO.save(sanpham);
		return "redirect:/admin_sanpham";
	}
	
	@PostMapping("/admin_sanpham/update")
	public String update1(@Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<SanPham> sanphams = sanphamDAO.findAll();
			model.addAttribute("sanphams", sanphams);
			return "/admin/admin_sanpham";
		}
		sanphamDAO.save(sanpham);
		return "redirect:/admin_sanpham/edit/" + sanpham.getMasp();
	}
	
	@RequestMapping("/admin_sanpham/delete/{masp}")
	public String create(@PathVariable("masp") Integer MaSP) {
		sanphamDAO.deleteById(MaSP);
		return "redirect:/admin_sanpham";
	}
	
	@PostMapping("/admin_sanpham/clear")
	public String clear(@ModelAttribute("sanpham") SanPham sanpham) {
		sanpham.setMasp(0);
		sanpham.setTensp(null);
		sanpham.setHinhanh1("default.png");
		sanpham.setHinhanh2("default.png");
		sanpham.setHinhanh3("default.png");
		sanpham.setLoai(true);
		sanpham.setGia(null);
		sanpham.setMota(null);
		sanpham.setTrangthai(true);
		return "redirect:/admin_sanpham";
	}
}
