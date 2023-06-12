package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.SessionService;

@Controller
public class thaydoithongtinController {
	public String messageCheckInputData = "";
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	NguoiDungDAO dao;

	@GetMapping("/thaydoithongtin")
	public String index(Model model) {
		NguoiDung user = sessionService.getSessionAttribute("user");
		if(user == null) {
			return "redirect:/dangnhap";
		}
		model.addAttribute("u", user);
		return "/nguoidung/thaydoithongtin";
	}
	
	@RequestMapping("/thaydoithongtin/luuthongtin")
	public String thaydoithongtin(Model model, @ModelAttribute("user") NguoiDung u,  @RequestParam("file") MultipartFile file) {
		System.out.println("----------Save----------");
		System.out.println("u: "+u);
		NguoiDung userSession = sessionService.getSessionAttribute("user");
		if(userSession == null) {
			return "redirect:/dangnhap";
		}
		if(!userSession.getSdt().equals(u.getSdt())) {	// So sánh. Nếu cập nhât SĐT
			if(!PhoneNumberCheckRegex(u.getSdt())) { // Check SĐT 
				model.addAttribute("message",this.messageCheckInputData);
				model.addAttribute("u", userSession);
				return "/nguoidung/thaydoithongtin";
			}
		}
		u.setHinh(file.getOriginalFilename());
		if(u.getHinh().equals("")) {
			u.setHinh(userSession.getHinh());
		}
		NguoiDung uFinal = dao.findByEmail(u.getEmail());
		uFinal.setHoten(u.getHoten());
		uFinal.setDiachi(u.getDiachi());
		uFinal.setHinh(u.getHinh());
		uFinal.setSdt(u.getSdt());
		dao.save(uFinal);
		System.out.println("id: "+uFinal.getMand());
		System.out.println("name: "+uFinal.getHoten());
		System.out.println("Dia chi: "+uFinal.getDiachi());
		model.addAttribute("message","Lưu thông tin hoàn tất");
		model.addAttribute("u", u);
		return "/nguoidung/thaydoithongtin";
	}
		
		public boolean PhoneNumberCheckRegex(String pn) {
			// PhoneNumber check
			if(pn.equals("")) {
				this.messageCheckInputData = "Lỗi: Bỏ trống số điện thoại!";
				return false;
			}
			String regexPN = "^(0[1-9][0-9]{8,9})$";
			boolean isValidPN = Pattern.matches(regexPN,pn);
			if(!isValidPN) {
				this.messageCheckInputData = "Lỗi: Số điện thoại không hợp lệ!";
				return false;
			}
			if(!checkPhoneNumberAlreadyExists(pn)) {
				this.messageCheckInputData = "Lỗi: Số điện thoại đã tồn tại";
				return false;
			}
			
			return true;
		}
		
		public  boolean checkPhoneNumberAlreadyExists(String pn) {
			List<NguoiDung> list = dao.findByPhoneNumber(pn);
			if(list.isEmpty()) {
				return true;
			}
			return false;
		}
}
