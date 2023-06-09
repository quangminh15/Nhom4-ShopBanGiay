package com.fpoly.ShopBanGiay.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.ShopBanGiay.dao.DanhMucDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.ParamService;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class admin_danhmucsanphamController {
	@Autowired
	SessionService session;
	
	@Autowired
	ParamService paramservice;

	@Autowired
	DanhMucDAO danhmucDAO;

	@GetMapping("/admin/admin_danhmucsanpham")
	public String admin_danhmucsp(Model model,  @RequestParam("p") Optional<Integer> p,@RequestParam("field") Optional<String> field) {
		DanhMuc danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc", danhmuc);
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("madm")).ascending());
		var list = danhmucDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", list);

		return "/admin/admin_danhmucsanpham";
	}

	@GetMapping("/admin_danhmucsanpham/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,@RequestParam("field") Optional<String> field) {
		return this.admin_danhmucsp(model,p,field);
	}

	@RequestMapping("/admin/admin_danhmucsanpham/create")
	public String add(@Valid @ModelAttribute("danhmuc") DanhMuc danhmuc, BindingResult result,Model model, @RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<DanhMuc> danhmucs = danhmucDAO.findAll();
			model.addAttribute("danhmucs", danhmucs);
			return "/admin/admin_danhmucsanpham";
		}
		try {
			danhmucDAO.save(danhmuc);
			model.addAttribute("success", "Thêm thành công!");
		} catch (Exception e) {
			model.addAttribute("error", "Thêm thất bại!");
		}
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("madm").ascending());
		var list = danhmucDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", list);
		danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc", danhmuc);

		return "/admin/admin_danhmucsanpham";
	}

	@RequestMapping("/admin/admin_danhmucsanpham/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("madm").ascending());
		var list = danhmucDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", list);
		DanhMuc danhmuc = danhmucDAO.findById(id).orElse(null);
		danhmuc.setAnhdm(danhmuc.getAnhdm());
		model.addAttribute("danhmuc", danhmuc);
		return "/admin/admin_danhmucsanpham";
	}

	@RequestMapping("/admin/admin_danhmucsanpham/delete/{madm}")
	public String remove(Model model, @PathVariable("madm") Integer id, @RequestParam("p") Optional<Integer> p) {
		
		try {
			danhmucDAO.deleteById(id);
			model.addAttribute("success", "Xóa thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Xóa thất bại: " + e);
			model.addAttribute("error", "Xóa thất bại!");
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("madm").ascending());
		var list = danhmucDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", list);
		DanhMuc danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc", danhmuc);
		return "/admin/admin_danhmucsanpham";
	}

	@RequestMapping("/admin/admin_danhmucsanpham/update")
	public String update(Model model,@Valid @ModelAttribute("danhmuc") DanhMuc danhmuc, BindingResult result,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<DanhMuc> danhmucs = danhmucDAO.findAll();
			model.addAttribute("danhmucs", danhmucs);
			return "/admin/admin_danhmucsanpham";
		}
		try {
			danhmucDAO.save(danhmuc);
			model.addAttribute("success", "Cập nhật thành công!");
		} catch (Exception e) {
			model.addAttribute("error", "Cập nhật thất bại!");
		}
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("madm").ascending());
		var list = danhmucDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", list);
		danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc", danhmuc);
		return "/admin/admin_danhmucsanpham";
	}

	@RequestMapping("/admin/admin_danhmucsanpham/clear")
	public String update(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("madm").ascending());
		var list = danhmucDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", list);
		DanhMuc danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc", danhmuc);
		return "/admin/admin_danhmucsanpham";
	}
	
	@RequestMapping("/admin/admin_danhmucsanpham/timkiem")
	public String getTimKiem(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("keywords") Optional<String> kw) {
		DanhMuc danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");

		model.addAttribute("danhmuc", danhmuc);
		
		String kwords = kw.orElse("");
		session.getSessionAttribute("keywords");
		session.setSessionAttribute("keywords", kwords);

		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("madm").ascending());
		Page<DanhMuc> danhmucs = danhmucDAO.findAllBytendmLike("%" + kwords + "%", pageable);
		if(danhmucs.isEmpty()) {
			model.addAttribute("message","Không có danh mục mà bạn muốn tìm kiếm");
			danhmucs = danhmucDAO.findAll(pageable);
		}
		var numberOfPages = danhmucs.getTotalPages();
		
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", danhmucs);
		return "/admin/admin_danhmucsanpham";
	}
}
