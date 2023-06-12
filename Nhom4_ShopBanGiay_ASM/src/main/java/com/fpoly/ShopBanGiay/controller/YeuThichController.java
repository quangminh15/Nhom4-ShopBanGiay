package com.fpoly.ShopBanGiay.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.dao.YeuThichDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
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
	
	@GetMapping("/yeuthich")
	public String DangNhap(Model model, YeuThich yt) {
		List<YeuThich> yeu = yeuthichDao.findYeuThichByID(2);
		model.addAttribute("yeus", yeu);
		return "/nguoidung/yeuthich";
	}
	
	@RequestMapping("/addyeuthich/{masp}")
	public String add1(@PathVariable("masp")Integer masp) {
		NguoiDung nguoidung = nguoidungDao.findById(2).get();
		Date da = ytimp.add(nguoidung, null, masp);
		return "redirect:/yeuthich";
	}
	
	@RequestMapping("/delete_yeuthich/{mayeuthich}")
	public String delete_yeuthich(Model model, @PathVariable(name = "mayeuthich") Integer mayeuthich) {
		yeuthichDao.deleteById(mayeuthich);
		List<YeuThich> yeu = yeuthichDao.findYeuThichByID(2);
		model.addAttribute("yeus", yeu);
		return "redirect:/yeuthich";
	}
	
}
