package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;

@Controller
public class admin_nguoidungController {

	@Autowired
	NguoiDungDAO dao;
	
	@GetMapping("/admin-nguoidung")
	public String admin_nguoidung(Model model) {
		System.out.println("----------Index----------");
		model.addAttribute("u", new NguoiDung());
		List<NguoiDung> list = dao.findAll();
		model.addAttribute("userList", list);
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/add")
	public String add(Model model, @ModelAttribute("user") NguoiDung u) {
	System.out.println("----------Add----------");
	System.out.println("User:"+u);
	dao.save(u);
	List<NguoiDung> list = dao.findAll();
	model.addAttribute("userList", list);
	model.addAttribute("u", new NguoiDung());
	System.out.println("----------***----------");
	return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		System.out.println("----------Edit----------");
	//	Optional<Category> item = cateDAO.findById(id);
		NguoiDung u = dao.findById(id).orElse(null); System.out.println("hinh"+u.getHinh());
		model.addAttribute("u", u);
		List<NguoiDung> list = dao.findAll();
		model.addAttribute("userList", list);
		System.out.println("Id:"+id);
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/remove/{mand}")
	public String remove(Model model, @PathVariable("mand") Integer id) {
		System.out.println("----------Remove----------");
			System.out.println("id:"+id);
			dao.deleteById(id);
			List<NguoiDung> list = dao.findAll();
			model.addAttribute("userList", list);
			model.addAttribute("u", new NguoiDung());
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	

	
//	@RequestMapping("/removeInList")
//	public String removeInList(Model model, @PathVariable("id") Integer id) {
//		System.out.println("----------Remove----------");
//		System.out.println("id:"+id);
//		if(id != null || id != 0 ) {
//			System.out.println("id:"+id);
//			dao.deleteById(id);
//			List<NguoiDung> list = dao.findAll();
//			model.addAttribute("userList", list);
//			model.addAttribute("u", new NguoiDung());
//		}
//		System.out.println("----------***----------");
//		return "/admin/admin_nguoidung";
//	}
	
	@RequestMapping("/admin-nguoidung/update")
	public String update(Model model, @ModelAttribute("user") NguoiDung u) {
		System.out.println("----------Update----------");
		dao.save(u);
		List<NguoiDung> list = dao.findAll();
		model.addAttribute("userList", list);
		model.addAttribute("u", new NguoiDung());
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/clear")
	public String update(Model model) {
		System.out.println("----------Clear----------");
		List<NguoiDung> list = dao.findAll();
		model.addAttribute("userList", list);
		model.addAttribute("u", new NguoiDung());
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
}
