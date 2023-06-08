package com.fpoly.ShopBanGiay.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.validation.Valid;

@Controller
public class admin_danhmucsanphamController {

	@Autowired
	DanhMucDAO danhmucDAO;
	
	private final String UPLOAD_DIR = "./uploads/";
	
	@RequestMapping("/admin_danhmucsanpham")
	public String admin_danhmucsanpham(DanhMuc danhmuc, Model model) {
		danhmuc = new DanhMuc();
		danhmuc.setAnhdm("default.png");
		model.addAttribute("danhmuc",danhmuc);
		List<DanhMuc> danhmucs = danhmucDAO.findAll();
		model.addAttribute("danhmucs", danhmucs);
		return "/admin/admin_danhmucsanpham";
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
	public String createdm(@Valid @ModelAttribute("danhmuc") DanhMuc danhmuc, BindingResult result, Model model,@RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			List<DanhMuc> danhmucs = danhmucDAO.findAll();
			model.addAttribute("danhmucs", danhmucs);
			return "/admin/admin_danhmucsanpham";
		}
		
		// check if file is empty
        if (file.isEmpty()) {
            model.addAttribute("error", "Chưa chọn ảnh");
            System.out.println("chưa chọn ảnh");
            return "/admin/admin_danhmucsanpham";
        }
		
		// normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
     // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        danhmuc.setAnhdm(fileName);
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
	
	@RequestMapping("/admin_size/delete/{madm}")
	public String deletedm(@PathVariable("madm") Integer MaDM) {
		danhmucDAO.deleteById(MaDM);
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
