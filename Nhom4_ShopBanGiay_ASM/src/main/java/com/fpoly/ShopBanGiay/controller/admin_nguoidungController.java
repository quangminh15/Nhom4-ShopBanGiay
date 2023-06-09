package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;


@Controller
public class admin_nguoidungController {

	@Autowired
	NguoiDungDAO dao;
	
	@GetMapping("/admin_nguoidung")
	public String admin_nguoidung() {
		return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/add")
	public String add(Model model, @ModelAttribute("user") NguoiDung u) {
	System.out.println("----------Add----------");
	System.out.println("cate:"+u);
	dao.save(u);
	List<NguoiDung> list = dao.findAll();
	model.addAttribute("userList", list);
	model.addAttribute("user", new NguoiDung());
	System.out.println("----------***----------");
	return "index";
	}
}
