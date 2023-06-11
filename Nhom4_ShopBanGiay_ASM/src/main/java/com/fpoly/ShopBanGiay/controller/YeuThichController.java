package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpoly.ShopBanGiay.dao.YeuThichDAO;
import com.fpoly.ShopBanGiay.model.YeuThich;

@Controller
public class YeuThichController {
	@Autowired
	YeuThichDAO yeuthichDao;
	
	@GetMapping("/yeuthich")
	public String DangNhap(Model model, YeuThich yt) {
		YeuThich yts = new YeuThich();
		model.addAttribute("yt", yts);
		List<YeuThich> yeu = yeuthichDao.findYeuThichByID(2);
		model.addAttribute("yeus", yeu);
		return "/nguoidung/yeuthich";
	}
}
