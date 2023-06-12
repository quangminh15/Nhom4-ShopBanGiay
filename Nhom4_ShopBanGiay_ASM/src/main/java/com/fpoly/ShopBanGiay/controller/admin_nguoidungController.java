package com.fpoly.ShopBanGiay.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;

@Controller
public class admin_nguoidungController {

	@Autowired
	NguoiDungDAO dao;
	
	@GetMapping("/admin/admin_nguoidung")
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
	
	@PostMapping("/admin/admin_nguoidung/add")
	public String add(Model model, @ModelAttribute("user") NguoiDung u,@RequestParam("p") Optional<Integer> p, @RequestParam("file") MultipartFile file) {
	System.out.println("----------Add----------");
	System.out.println("User:"+u);
	if(!checkEmailAlreadyExists(u.getEmail())) {
		model.addAttribute("message","Lỗi: Email này đã tồn tại!");
		model.addAttribute("u", new NguoiDung());
		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		return "/admin/admin_nguoidung";
	}
//	if(!handleFileUpload(file)) {
//		model.addAttribute("message","Lỗi: Upload hình!");
//		model.addAttribute("u", new NguoiDung());
//		Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by("hoten").ascending());
//		var list = dao.findAll(pageable);
//		var numberOfPages = list.getTotalPages();
//		model.addAttribute("currIndex", p.orElse(0));
//	    model.addAttribute("numberOfPages", numberOfPages);
//	    model.addAttribute("userList", list);
//		return "/admin/admin_nguoidung";
//	}
	u.setHinh(file.getOriginalFilename());
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
	
//	@PostMapping("/admin/admin_nguoidung/add")
//	  public String add(@RequestParam("hinh") MultipartFile file) {
//		 try {
//		        String targetDirectory = "./images/userImages"; // Đường dẫn thư mục đích
//		        String fileName = file.getOriginalFilename(); // Lấy tên file gốc 
//		        System.out.println("Tên FIle: "+fileName);
//		        System.out.println("TM: "+targetDirectory);
//		        
//		        // Tạo đối tượng File đại diện cho thư mục đích
//		        File directory = new File(targetDirectory);
//		        if (!directory.exists()) {
//		            directory.mkdirs(); // Tạo thư mục đích nếu nó chưa tồn tại
//		        }
//		        
//		        // Di chuyển file tải lên vào thư mục đích
//		        File destinationFile = new File(directory, fileName);
//		        file.transferTo(destinationFile);
//		        
// 
//		    } catch (IOException e) {
//		        e.printStackTrace();
// 
//		    }
//	    return "/admin/admin_nguoidung";
//	  }
	
	@RequestMapping("/admin/admin_nguoidung/edit/{id}")
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
	
	@RequestMapping("/admin/admin_nguoidung/remove/{mand}")
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
	

	
	@RequestMapping("/admin/admin_nguoidung/update")
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
	
	@RequestMapping("/admin/admin_nguoidung/clear")
	public String clear(Model model, @RequestParam("p") Optional<Integer> p) {
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
	
	public  boolean checkEmailAlreadyExists(String email) {
		List<NguoiDung> list = dao.findByEmails(email);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	public boolean handleFileUpload(MultipartFile file) {
	    try {
	        String targetDirectory = "/images/userImages"; // Đường dẫn thư mục đích
	        String fileName = file.getOriginalFilename(); // Lấy tên file gốc 
	        System.out.println("Tên FIle: "+fileName);
	        
	        // Tạo đối tượng File đại diện cho thư mục đích
	        File directory = new File(targetDirectory);
	        if (!directory.exists()) {
	            directory.mkdirs(); // Tạo thư mục đích nếu nó chưa tồn tại
	        }
	        
	        // Di chuyển file tải lên vào thư mục đích
	        File destinationFile = new File(directory, fileName);
	        file.transferTo(destinationFile);
	        
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
//	@RequestMapping("/lol")
//	public String handleFileUpload(@RequestParam("hinh") MultipartFile file) {
//	    try {
//	        String targetDirectory = "/images/userImages"; // Đường dẫn thư mục đích
//	        String fileName = file.getOriginalFilename(); // Lấy tên file gốc 
//	        System.out.println("Tên FIle: "+fileName);
//	        
//	        // Tạo đối tượng File đại diện cho thư mục đích
//	        File directory = new File(targetDirectory);
//	        if (!directory.exists()) {
//	            directory.mkdirs(); // Tạo thư mục đích nếu nó chưa tồn tại
//	        }
//	        
//	        // Di chuyển file tải lên vào thư mục đích
//	        File destinationFile = new File(directory, fileName);
//	        file.transferTo(destinationFile);
//	        
//	        return "/admin/admin_nguoidung";
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return "/admin/admin_nguoidung";
//	    }
//	}

}
