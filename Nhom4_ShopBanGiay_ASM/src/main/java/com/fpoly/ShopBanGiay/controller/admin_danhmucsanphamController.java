package com.fpoly.ShopBanGiay.controller;

import java.io.IOException;
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
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.ParamService;

import jakarta.validation.Valid;

@Controller
public class admin_danhmucsanphamController {

	@Autowired
	ParamService paramservice;
	
	@Autowired
	DanhMucDAO danhmucDAO;

	@RequestMapping("/admin_danhmucsanpham")
	public String admin_danhmucsanpham(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		DanhMuc danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc", danhmuc);

		Pageable pageable = PageRequest.of(p.orElse(0), 4, Sort.by(Direction.DESC, field.orElse("madm")).ascending());
		Page<DanhMuc> danhmucs = danhmucDAO.findAll(pageable);

		var numberOfPages = danhmucs.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("danhmucs", danhmucs);
		return "/admin/admin_danhmucsanpham";
	}

	@GetMapping("/admin_danhmucsanpham/page")
	public String pagedanhmuc(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		return this.admin_danhmucsanpham(model, field, p);
	}

	@RequestMapping("/admin_danhmucsanpham/edit/{madm}")
	public String editdm(Model model, @PathVariable("madm") Integer MaDM) {
		DanhMuc danhmuc = danhmucDAO.findById(MaDM).get();
		model.addAttribute("danhmuc", danhmuc);
		List<DanhMuc> danhmucs = danhmucDAO.findAll();
		model.addAttribute("danhmucs", danhmucs);
		return "/admin/admin_danhmucsanpham";
	}

	@PostMapping("/admin_danhmucsanpham/create")
	public String createdm(@Valid @ModelAttribute("danhmuc") DanhMuc danhmuc, BindingResult result, Model model,
			@RequestParam("image") MultipartFile multipartfile) throws IOException {
		
		if (result.hasErrors()) {
			List<DanhMuc> danhmucs = danhmucDAO.findAll();
			model.addAttribute("danhmucs", danhmucs);
			return "/admin/admin_danhmucsanpham";
		}

		String filename = StringUtils.cleanPath(multipartfile.getOriginalFilename());
		String uploadDir = "/imageSP";
		ParamService.saveFile(uploadDir, filename, multipartfile);
		
		danhmuc.setAnhdm(filename);
		danhmucDAO.save(danhmuc);
		return "redirect:/admin_danhmucsanpham";
	}

	@PostMapping("/admin_danhmucsanpham/update")
	public String updatedm(@Valid @ModelAttribute("danhmuc") DanhMuc danhmuc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<DanhMuc> danhmucs = danhmucDAO.findAll();
			model.addAttribute("danhmucs", danhmucs);
			return "/admin/admin_danhmucsanpham";
		}
		danhmucDAO.save(danhmuc);
		return "redirect:/admin_danhmucsanpham/edit/" + danhmuc.getMadm();
	}

	@RequestMapping("/delete/{madm}")
	public String deletedm(@PathVariable("madm") Integer madm) {
		danhmucDAO.deleteById(madm);
		return "redirect:/admin_danhmucsanpham";
	}

	@PostMapping("/admin_danhmucsanpham/clear")
	public String clear(@ModelAttribute("danhmuc") DanhMuc danhmuc) {
		danhmuc.setMadm(0);
		danhmuc.setTendm(null);
		danhmuc.setAnhdm("default.png");
		danhmuc.setTrangthai(true);
		return "redirect:/admin_danhmucsanpham";
	}
}
