package com.fpoly.ShopBanGiay.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.MailerService;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class DoiMatKhauController {
	
	@Autowired
	MailerService mail;
	
	@Autowired
	NguoiDungDAO nguoiDungDAO;
	
	NguoiDung user = new NguoiDung();
	private String code;
	private String pass = "";
	
	@Autowired
	SessionService sessionService;
	
	NguoiDung userSession =  new NguoiDung();

	@GetMapping("/doimatkhau")
	public String doimatkhau(Model model) {
		userSession = new NguoiDung(sessionService.getSessionAttribute("user"));
		user.setEmail(userSession.getEmail());
		model.addAttribute("nguoidung", user);
		return "/nguoidung/doimatkhau";
	}
	
	@PostMapping("/doimatkhau/nguoidung")
	public String changePass(@Valid @ModelAttribute("nguoidung") NguoiDung nguoidung,BindingResult result, Model model) {System.out.println("Thông tin đổi mật khẩu: "+nguoidung);
		if(nguoidung != null) {
			System.out.println("u: "+nguoidung);
			System.out.println("Đang xác thực pass...");
			user = new NguoiDung(nguoiDungDAO.findByEmail(nguoidung.getEmail()));
			this.pass = nguoidung.getDiachi();
			System.out.println("Mật khẩu cũ: "+user.getMatkhau());
			if(!nguoidung.getMatkhau().equalsIgnoreCase(user.getMatkhau())) {
				System.out.println("chưa khớp");
				model.addAttribute("messageConfirmPassWrong", "Lỗi: Mật khẩu cũ không chính xác");
				nguoidung.setMatkhau("");
				model.addAttribute("nguoidung", nguoidung);
				return "/nguoidung/doimatkhau";
			}
			if(!nguoidung.getDiachi().equalsIgnoreCase(nguoidung.getHinh())) {
				System.out.println("chưa khớp");
				model.addAttribute("messageConfirmPassWrong", "Lỗi: Mật khẩu mới và xác nhận mật khẩu chưa khớp!");
				nguoidung.setMatkhau(user.getMatkhau());
				nguoidung.setHinh("");
				nguoidung.setDiachi("");
				model.addAttribute("nguoidung", nguoidung);
				return "/nguoidung/doimatkhau";
			}
			return "redirect:/doimatkhau/xacnhan";
		}
		
		if (result.hasErrors()) {
		    return "/nguoidung/doimatkhau";
		}
		return "/nguoidung/doimatkhau";
	}
	
	@RequestMapping("/doimatkhau/xacnhanS2")
	public String xacnhanB2(Model model, @RequestParam("code") String codeFormUser) {
		System.out.println("Mã code user nhập: "+codeFormUser);
		System.out.println("Mã code: "+code);
		if(code.equals(codeFormUser)) {
			System.out.println("------mkm----- "+this.pass);
			userSession.setMatkhau(this.pass);
			nguoiDungDAO.save(userSession);
			return "redirect:/logout";
		}
		model.addAttribute("messageConfirmPassWrong", "Mã xác thực chưa đúng!");
		return "/nguoidung/confirmCodeChangePass";
	}
	
	@RequestMapping("/doimatkhau/xacnhan")
	public String xacnhandoimatkhau(Model model) {
		System.out.println("Thông tin đk: "+user);
		code = generateCode(6);
		System.out.println("Mã code: "+code);
		mail.queue(user.getEmail(), "ĐỔI MẬT KHẨU CONVERSE", "Mã xác thực: "+code);
		return "/nguoidung/confirmCodeChangePass";
	}
	
	
	
	public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        
        return sb.toString();
    }
}
