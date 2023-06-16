package com.fpoly.ShopBanGiay.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.dao.YeuThichDAO;
import com.fpoly.ShopBanGiay.model.GiamGia;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.YeuThich;
import com.fpoly.ShopBanGiay.service.SessionService;
import com.fpoly.ShopBanGiay.service.YtServiceImp;

@Controller
public class YeuThichController {
	@Autowired
	YeuThichDAO yeuthichDao;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	NguoiDungDAO nguoidungDao;
	
	@Autowired
	YtServiceImp ytimp;
	
	@Autowired
	SanPhamDAO sanphamDAO;
	
	@Autowired
	SanPhamSizeDAO spsizeDAO;
	
	@Autowired
	SessionService session;
	
	
	@GetMapping("/yeuthich")
	public String DangNhap(Model model, YeuThich yt) {
		
		NguoiDung nguoidung = new NguoiDung(sessionService.getSessionAttribute("user"));
		List<YeuThich> yeu = yeuthichDao.findYeuThichByID(nguoidung.getMand());
		model.addAttribute("yeus", yeu);
		model.addAttribute("user", session.getSessionAttribute("user"));
		return "/nguoidung/yeuthich";
	}
	
	@RequestMapping("/addyeuthich/{masp}")
	public String add1(@PathVariable("masp")Integer masp, Model model, YeuThich yt) {
		Optional<YeuThich> y = yeuthichDao.findById(masp);
		NguoiDung nguoidung1 = new NguoiDung(sessionService.getSessionAttribute("user"));
		if (!check(masp)) {

		return "redirect:/yeuthich";
			}
		
		
		NguoiDung nguoidung = nguoidungDao.findById(nguoidung1.getMand()).get();
		Date da = ytimp.add(nguoidung, null, masp);
		
			
//
//	if(y.isPresent()) {
//		NguoiDung nguoidung = nguoidungDao.findById(nguoidung1.getMand()).get();
//		Date da = ytimp.add(nguoidung, null, masp);
//		}
//			NguoiDung nguoidung = nguoidungDao.findById(nguoidung1.getMand()).get();
//			Date da = ytimp.add(nguoidung, null, masp);
		return "redirect:/yeuthich";
	}
	
	@RequestMapping("/delete_yeuthich/{mayeuthich}")
	public String delete_yeuthich(Model model, @PathVariable(name = "mayeuthich") Integer mayeuthich) {
		NguoiDung nguoidung = new NguoiDung(sessionService.getSessionAttribute("user"));
//		System.out.println("u:" +nguoidung);
		List<YeuThich> yeu = yeuthichDao.findYeuThichByID(nguoidung.getMand());
		yeuthichDao.deleteById(mayeuthich);
		model.addAttribute("yeus", yeu);
		return "redirect:/yeuthich";
	}
	
	@RequestMapping("/sanpham1/chitietsp/{masp}")
	public String chitietspa(Model model, @PathVariable("masp") Integer masp, @Param("masps") Integer masps,
			@RequestParam("keywords") Optional<String> kw) {
		SanPham sanpham = sanphamDAO.findById(masp).orElse(null);
		model.addAttribute("sanpham", sanpham);
		
		String kwords = kw.orElse(sanpham.getDanhmuc().getTendm());
		sessionService.getSessionAttribute("keywords");
		sessionService.setSessionAttribute("keywords", kwords);
		List<SanPham> sanphams = sanphamDAO.findByTenDM("%" + kwords + "%");
		model.addAttribute("sanphams", sanphams);

		List<SanPhamSize> spsizes = spsizeDAO.findByMaSP(masp);
		model.addAttribute("sizes", spsizes);

		return "/nguoidung/chitietsp";
	}
	
	public boolean checkSP(Integer masp) {
		List<YeuThich> list = yeuthichDao.findYeuThichSP(masp);
		if(list.size() < 0) {
			return true;
		}
		return false;
	}
	
	public boolean check(Integer masp) {
		if(!checkSP(masp)) {
			return false;
		}
		return true;
	}
	
}
