package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;

@Controller
public class admin_nguoidungController {

	@Autowired
	NguoiDungDAO dao;
	
	@GetMapping("/admin-nguoidung")
	public String admin_nguoidung(Model model, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Index----------");
		model.addAttribute("u", new NguoiDung());
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@GetMapping("/pageNguoiDung")
    public String paginate(Model model,@RequestParam("p") Optional<Integer> p){
        return this.admin_nguoidung(model, p);
    }
	
	@RequestMapping("/admin-nguoidung/add")
	public String add(Model model, @ModelAttribute("user") NguoiDung u,@RequestParam("p") Optional<Integer> p) {
	System.out.println("----------Add----------");
	System.out.println("User:"+u);
	dao.save(u);
	Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
	var list = dao.findAll(pageable);
	var numberOfPages = list.getTotalPages();
	model.addAttribute("currIndex", p.orElse(0));
    model.addAttribute("numberOfPages", numberOfPages);
    model.addAttribute("userList", list);
	model.addAttribute("u", new NguoiDung());
	System.out.println("----------***----------");
	return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Edit----------");
	//	Optional<Category> item = cateDAO.findById(id);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		NguoiDung u = dao.findById(id).orElse(null); System.out.println("hinh"+u.getHinh());
		model.addAttribute("u", u);
		System.out.println("Id:"+id);
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/remove/{mand}")
	public String remove(Model model, @PathVariable("mand") Integer id, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Remove----------");
			System.out.println("id:"+id);
			dao.deleteById(id);
			Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
			var list = dao.findAll(pageable);
			var numberOfPages = list.getTotalPages();
			model.addAttribute("currIndex", p.orElse(0));
		    model.addAttribute("numberOfPages", numberOfPages);
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
	public String update(Model model, @ModelAttribute("user") NguoiDung u, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Update----------");
		dao.save(u);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		model.addAttribute("u", new NguoiDung());
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@RequestMapping("/admin-nguoidung/clear")
	public String update(Model model, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Clear----------");
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		model.addAttribute("u", new NguoiDung());
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
}
