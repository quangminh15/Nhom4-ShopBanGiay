package com.fpoly.ShopBanGiay.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NhaCungCapDAO;
import com.fpoly.ShopBanGiay.model.GiamGia;
import com.fpoly.ShopBanGiay.model.NhaCungCap;
import com.fpoly.ShopBanGiay.model.Size;

import jakarta.validation.Valid;

@Controller
public class admin_nhacungcapController {
	@Autowired
	NhaCungCapDAO nhacungcapDAO;
	String check = "";
	Boolean count = true;
	
	@GetMapping("/admin/admin_nhacungcap")
	public String admin_nhacungcap(Model model, @RequestParam("p") Optional<Integer> p, @RequestParam("field") Optional<String> field) {
		NhaCungCap n = new NhaCungCap();
		model.addAttribute("NCCS", n);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("mancc")).ascending());
		var nhacungcap1 = nhacungcapDAO.findAll(pageable);
		var numberOfPages = nhacungcap1.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("NCC", nhacungcap1);
		return "/admin/admin_nhacungcap";
	}
	
	@GetMapping("/page1")
	public String page1(Model model, @RequestParam("p") Optional<Integer> p, @RequestParam("field") Optional<String> field) {
		return this.admin_nhacungcap(model, p, field);
	}
	
	@PostMapping("/admin/save_nhacungcap")
	public String save_nhacungcap(@Valid @ModelAttribute("NCCS") NhaCungCap nhacungcap, BindingResult result, Model model, @RequestParam("p") Optional<Integer> p) {
		if(result.hasErrors()) {
			model.addAttribute("NCC", nhacungcapDAO.findAll());
			Pageable pageable = PageRequest.of(p.orElse(0), 5);
			Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

			var numberOfPages = nccss.getTotalPages();

			model.addAttribute("currIndex", p.orElse(0));
			model.addAttribute("numberOfPages", numberOfPages);

			model.addAttribute("NCC", nccss);
			return "/admin/admin_nhacungcap"; 
		}else if(nhacungcap !=  null) {
			if(!check(nhacungcap)) {
				model.addAttribute("message", this.check);
				model.addAttribute("NCCS", nhacungcap);
				model.addAttribute("NCC", nhacungcapDAO.findAll());
				Pageable pageable = PageRequest.of(p.orElse(0), 5);
				Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

				var numberOfPages = nccss.getTotalPages();

				model.addAttribute("currIndex", p.orElse(0));
				model.addAttribute("numberOfPages", numberOfPages);

				model.addAttribute("NCC", nccss);
			    return "/admin/admin_nhacungcap"; 
			}
		}
		
		nhacungcapDAO.save(nhacungcap);
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		model.addAttribute("message1", "Thêm thành công");
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

		var numberOfPages = nccss.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("NCC", nccss);
		
		return "/admin/admin_nhacungcap";
	}
	
	@RequestMapping("/admin/update_nhacungcap")
	public String update_nhacungcap(@Valid @ModelAttribute("NCCS") NhaCungCap nhacungcap, BindingResult result, Model model, @RequestParam("p") Optional<Integer> p) {
		if(result.hasErrors()) {
			model.addAttribute("NCC", nhacungcapDAO.findAll());
			Pageable pageable = PageRequest.of(p.orElse(0), 5);
			Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

			var numberOfPages = nccss.getTotalPages();

			model.addAttribute("currIndex", p.orElse(0));
			model.addAttribute("numberOfPages", numberOfPages);

			model.addAttribute("NCC", nccss);
			return "/admin/admin_nhacungcap"; 
		}
		
		nhacungcapDAO.save(nhacungcap);
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		model.addAttribute("message1", "Cập nhật thành công");
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

		var numberOfPages = nccss.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("NCC", nccss);
		
		
		return "/admin/admin_nhacungcap";
	}
	
	
	@RequestMapping("/edit_nhacungcap/{mancc}")
	public String eidt_nhacungcap(Model model, @PathVariable(name="mancc") Integer mancc,  @RequestParam("p") Optional<Integer> p) {
		Optional<NhaCungCap> n = nhacungcapDAO.findById(mancc);
		if(n.isPresent()) {
			model.addAttribute("NCCS", n.get());
			model.addAttribute("NCC", nhacungcapDAO.findAll());
		}
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

		var numberOfPages = nccss.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("NCC", nccss);
		return "/admin/admin_nhacungcap";
	}
	
	@RequestMapping("/delete_nhacungcap/{mancc}")
	public String delete_nhacungcap(Model model, @PathVariable(name="mancc") Integer mancc, @RequestParam("p") Optional<Integer> p) {
		NhaCungCap n = new NhaCungCap();
		nhacungcapDAO.deleteById(mancc);
		model.addAttribute("NCCS", n);
		model.addAttribute("NCC", nhacungcapDAO.findAll());
		model.addAttribute("message1", "Xóa thành công");
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhaCungCap> nccss = nhacungcapDAO.findAll(pageable);

		var numberOfPages = nccss.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("NCC", nccss);
		return "/admin/admin_nhacungcap";
	}
	
	@PostMapping("/admin_nhacungcap/clear")
	public String clear_nhacungcap(@ModelAttribute("nhacungcap") NhaCungCap nhacungcap) {
		nhacungcap.setMancc(null);
		nhacungcap.setTenncc(null);
		nhacungcap.setEmail(null);
		nhacungcap.setDiachi(null);
		nhacungcap.setSdt(null);
		
		return "redirect:/admin/admin_nhacungcap";
	}
	
	public boolean checkPhone(String phone) {
		List<NhaCungCap> list = nhacungcapDAO.findByPhone(phone);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean checkEmail(String email) {
		List<NhaCungCap> list = nhacungcapDAO.findByEmail(email);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean checkTenncc(String tenncc) {
		List<NhaCungCap> list = nhacungcapDAO.findByTenncc(tenncc);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean check(NhaCungCap nhacungcap) {
		if(!checkTenncc(nhacungcap.getTenncc())) {
			this.check = "Tên nhà cung cấp đã tồn tại vui lòng nhập tên mới";
			return false;
		} else if(!checkEmail(nhacungcap.getEmail())) {
			this.check = "Email này đã tồn tại vui lòng nhập Email mới";
			return false;
		}else if(!checkPhone(nhacungcap.getSdt())) {
			this.check = "SDT này đã tồn tại vui lòng nhập SDT mới";
			return false;
		}
		return true;
	}
	
	
}
