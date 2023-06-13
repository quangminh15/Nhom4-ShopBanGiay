package com.fpoly.ShopBanGiay.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.service.SessionService;

@Controller
public class admin_nguoidungController {

	@Autowired
	NguoiDungDAO dao;
	
	@Autowired
	SessionService sessionService;
	
//	public String imgEdit = "";
	
	NguoiDung nguoiDungEdit = new NguoiDung();
	
	public String messageCheckInputData = "";
	
	String nameSearch = "";
	
	@GetMapping("/admin/admin_nguoidung")
	public String admin_nguoidung(Model model, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Index----------");
		model.addAttribute("u", new NguoiDung());
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		this.nameSearch = "";
		NguoiDung nguoiDungEdit = new NguoiDung();
		System.out.println("----------***----------");
		return "/admin/admin_nguoidung";
	}
	
	@GetMapping("/pageNguoiDung")
    public String paginate(Model model,@RequestParam("p") Optional<Integer> p){
		return this.search(model, p, this.nameSearch);
    }
	
	@PostMapping("/admin/admin_nguoidung/add")
	public String add(Model model, @ModelAttribute("user") NguoiDung u,@RequestParam("p") Optional<Integer> p, @RequestParam("file") MultipartFile file) {
	System.out.println("----------Add----------");
	System.out.println("User:"+u);
	System.out.println(file.getOriginalFilename());
	u.setHinh(file.getOriginalFilename());
	if(u.getHinh().equals("")) {
		u.setHinh(this.nguoiDungEdit.getHinh());
	}
	if(!checkDataInputUser(u)) {
		model.addAttribute("message",this.messageCheckInputData);
		model.addAttribute("u", u);
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		return "/admin/admin_nguoidung";
	}
	if(!checkEmailAlreadyExists(u.getEmail())) {
		model.addAttribute("message","Lỗi: Email này đã tồn tại!");
		model.addAttribute("u", u);
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
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
//		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
//		var list = dao.findAll(pageable);
//		var numberOfPages = list.getTotalPages();
//		model.addAttribute("currIndex", p.orElse(0));
//	    model.addAttribute("numberOfPages", numberOfPages);
//	    model.addAttribute("userList", list);
//		return "/admin/admin_nguoidung";
//	}
	dao.save(u);
	Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
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
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		NguoiDung u = dao.findById(id).orElse(null); System.out.println("hinh"+u.getHinh());
		model.addAttribute("u", u);
		this.nguoiDungEdit =  new NguoiDung(u); // sao chép đối tượng
		System.out.println("TT edit:"+this.nguoiDungEdit.isTrangthai());
		System.out.println("Id edit:"+id);
		System.out.println("----------***----------");
		return this.search(model, p, this.nameSearch);
	}
	
//	@RequestMapping("/admin/admin_nguoidung/remove/{mand}")
//	public String remove(Model model, @PathVariable("mand") Integer id, @RequestParam("p") Optional<Integer> p) {
//		System.out.println("----------Remove----------");
//		System.out.println(id);
//		NguoiDung nd = dao.getUserByIdSure(id);
//		
//		if(nd.isVaitro()) {
//			if(dao.countAdmin() < 3) {
//				model.addAttribute("message","Lỗi: Không thể xoá, Tối thiểu phải có 2 admin!");
//				model.addAttribute("u", dao.getUserByIdSure(id));
//				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
//				var list = dao.findAll(pageable);
//				var numberOfPages = list.getTotalPages();
//				model.addAttribute("currIndex", p.orElse(0));
//			    model.addAttribute("numberOfPages", numberOfPages);
//			    model.addAttribute("userList", list);
//				return "/admin/admin_nguoidung";
//			}
//			if(!checkSelfDestruct(nd.getMand())) {
//				model.addAttribute("message",this.messageCheckInputData);
//				model.addAttribute("u", dao.getUserByIdSure(id));
//				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
//				var list = dao.findAll(pageable);
//				var numberOfPages = list.getTotalPages();
//				model.addAttribute("currIndex", p.orElse(0));
//			    model.addAttribute("numberOfPages", numberOfPages);
//			    model.addAttribute("userList", list);
//				return "/admin/admin_nguoidung";
//			}
//		}System.out.println("đã đến");
//		
//			System.out.println("id:"+id);
//			dao.deleteById(id);
//			Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
//			var list = dao.findAll(pageable);
//			var numberOfPages = list.getTotalPages();
//			model.addAttribute("currIndex", p.orElse(0));
//		    model.addAttribute("numberOfPages", numberOfPages);
//			model.addAttribute("userList", list);
//			model.addAttribute("u", new NguoiDung());
//		System.out.println("----------***----------");
//		return "/admin/admin_nguoidung";
//	}
	
	@RequestMapping("/admin/admin_nguoidung/remove/{mand}")
	public String remove(Model model, @PathVariable("mand") Integer id, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Remove----------");
		System.out.println(id);
		NguoiDung nd = dao.getUserByIdSure(id);
		
		if(nd.isVaitro()) {
			if(dao.countAdmin() < 3) {
				model.addAttribute("message","Lỗi: Không thể xoá, Tối thiểu phải có 2 admin!");
				model.addAttribute("u", dao.getUserByIdSure(id));
				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
				var list = dao.findAll(pageable);
				var numberOfPages = list.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
			    model.addAttribute("numberOfPages", numberOfPages);
			    model.addAttribute("userList", list);
			    return this.search(model, p, this.nameSearch);
			}
			if(!checkSelfDestruct(nd.getMand())) {
				model.addAttribute("message",this.messageCheckInputData);
				model.addAttribute("u", dao.getUserByIdSure(id));
				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
				var list = dao.findAll(pageable);
				var numberOfPages = list.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
			    model.addAttribute("numberOfPages", numberOfPages);
			    model.addAttribute("userList", list);
			    return this.search(model, p, this.nameSearch);
			}
		}System.out.println("đã đến");
		
			System.out.println("id:"+id);
			nd.setTrangthai(true);
			dao.save(nd);
			Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
			var list = dao.findAll(pageable);
			var numberOfPages = list.getTotalPages();
			model.addAttribute("currIndex", p.orElse(0));
		    model.addAttribute("numberOfPages", numberOfPages);
			model.addAttribute("userList", list);
		    model.addAttribute("message","Xoá người dùng thành công");
			this.nguoiDungEdit = new NguoiDung();
		System.out.println("----------***----------");
		return this.search(model, p, this.nameSearch);
	}
	

	
	@RequestMapping("/admin/admin_nguoidung/update")
	public String update(Model model, @ModelAttribute("user") NguoiDung u, @RequestParam("p") Optional<Integer> p,  @RequestParam("file") MultipartFile file) {
		System.out.println("----------Update----------");
		if(this.nguoiDungEdit == null) {
			this.nguoiDungEdit = new NguoiDung(dao.getUserByIdSure(u.getMand()));
		}
		if(!checkSessionAdminUpdateToCusomer(u)) {
			model.addAttribute("message",this.messageCheckInputData);
			model.addAttribute("u", this.nguoiDungEdit);
			Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
			var list = dao.findAll(pageable);
			var numberOfPages = list.getTotalPages();
			model.addAttribute("currIndex", p.orElse(0));
		    model.addAttribute("numberOfPages", numberOfPages);
		    model.addAttribute("userList", list);
		    return this.search(model, p, this.nameSearch);
		}
		if(!this.nguoiDungEdit.getEmail().equals(u.getEmail())) { // So sánh. Nếu Cập nhật Email
			if(!EmailCheckRegex(u.getEmail())) { // Check Email có hợp lệ?
				model.addAttribute("message",this.messageCheckInputData);
				model.addAttribute("u", this.nguoiDungEdit);
				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
				var list = dao.findAll(pageable);
				var numberOfPages = list.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
			    model.addAttribute("numberOfPages", numberOfPages);
			    model.addAttribute("userList", list);
			    return this.search(model, p, this.nameSearch);
			}
			if(!checkEmailAlreadyExists(u.getEmail())) { // Check Email đã tồn tại chưa?
				model.addAttribute("message","Lỗi: Email này đã tồn tại!");
				model.addAttribute("u", this.nguoiDungEdit);
				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
				var list = dao.findAll(pageable);
				var numberOfPages = list.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
			    model.addAttribute("numberOfPages", numberOfPages);
			    model.addAttribute("userList", list);
			    return this.search(model, p, this.nameSearch);
			}
		}
		if(!this.nguoiDungEdit.getSdt().equals(u.getSdt())) {	// So sánh. Nếu cập nhât SĐT
			if(!PhoneNumberCheckRegex(u.getSdt())) { // Check SĐT có hợp lệ?
				model.addAttribute("message",this.messageCheckInputData);
				model.addAttribute("u", this.nguoiDungEdit);
				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
				var list = dao.findAll(pageable);
				var numberOfPages = list.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
			    model.addAttribute("numberOfPages", numberOfPages);
			    model.addAttribute("userList", list);
			    return this.search(model, p, this.nameSearch);
			}
			if(!PhoneNumberCheckRegex(u.getSdt())) { // Check SĐT đã tồn tại chưa?
				model.addAttribute("message",this.messageCheckInputData);
				model.addAttribute("u", this.nguoiDungEdit);
				Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
				var list = dao.findAll(pageable);
				var numberOfPages = list.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
			    model.addAttribute("numberOfPages", numberOfPages);
			    model.addAttribute("userList", list);
			    return this.search(model, p, this.nameSearch);
			}
		}
		u.setHinh(file.getOriginalFilename());
		if(u.getHinh().equals("")) {
			u.setHinh(this.nguoiDungEdit.getHinh());
		}
		dao.save(u);
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		model.addAttribute("u", new NguoiDung());
		model.addAttribute("message","Cập nhật thông tin thành công");
		this.nguoiDungEdit = new NguoiDung();
		System.out.println("----------***----------");
		return this.search(model, p, this.nameSearch);
	}
	
	@RequestMapping("/admin/admin_nguoidung/clear")
	public String clear(Model model, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Clear----------");
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		this.nguoiDungEdit = new NguoiDung();
		System.out.println("----------***----------");
		return this.search(model, p, this.nameSearch);
	}
	
	
	// restore
	@RequestMapping("/admin/admin_nguoidung/restore/{id}")
	public String restore(Model model, @PathVariable("id") Integer id, @RequestParam("p") Optional<Integer> p) {
		System.out.println("----------Restore----------");
		NguoiDung uRestore = dao.getUserByIdSure(id);
		uRestore.setTrangthai(false);
		dao.save(uRestore);
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		var list = dao.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
	    model.addAttribute("numberOfPages", numberOfPages);
	    model.addAttribute("userList", list);
		model.addAttribute("u", uRestore);
		model.addAttribute("message","Khôi phục người dùng thành công");
		this.nguoiDungEdit = new NguoiDung();
		System.out.println("----------***----------");
		return this.search(model, p, this.nameSearch);
	}
	
	// search
	@RequestMapping("/admin/admin_nguoidung/searchByName")
	public String search(Model model, @RequestParam("p") Optional<Integer> p,@RequestParam("nameSearch")  String string) {
		System.out.println("----------Search----------");
		String uName = string;
		this.nameSearch = string;
		System.out.println("Name: "+uName);
		Pageable pageable = PageRequest.of(p.orElse(0), 10, Sort.by("trangthai").ascending());
		System.out.println(1);
		var list = dao.findAllByHotenLike("%"+uName+"%", pageable);System.out.println(2);
		var numberOfPages = list.getTotalPages();System.out.println(3);
		model.addAttribute("currIndex", p.orElse(0));System.out.println(4);
	    model.addAttribute("numberOfPages", numberOfPages);System.out.println(5);
	    model.addAttribute("userList", list);
		model.addAttribute("u", this.nguoiDungEdit);
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
	
	public  boolean checkPhoneNumberAlreadyExists(String pn) {
		List<NguoiDung> list = dao.findByPhoneNumber(pn);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}

	
	public boolean checkDataInputUser(NguoiDung u) {
		// Name check
		if(u.getHoten().equals("")) {
			this.messageCheckInputData = "Lỗi: họ tên phải từ 0-50 ký tự và không chứ ký tự đặc biệt!";
			return false;
		}
		String regexName = "^[A-Za-z0-9\\p{L}\\s]{1,50}$";
		boolean isValidName = Pattern.matches(regexName, u.getHoten());
		if(!isValidName) {
			this.messageCheckInputData = "Lỗi: họ tên phải từ 0-50 ký tự và không chứ ký tự đặc biệt!";
			return false;
		}
		
		// Email check
		if(u.getEmail().equals("")) {
			this.messageCheckInputData = "Lỗi: Không được bỏ trống Email!";
			return false;
		}
		String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|gov|edu|vn|us|uk|au|ca)$";
		Pattern EMAIL_PATTERN = Pattern.compile(regexEmail);
		System.out.println("Email: "+u.getEmail());
		if(!(EMAIL_PATTERN.matcher(u.getEmail()).matches()) || u.getEmail().length() > 50) {
			this.messageCheckInputData = "Lỗi: Định dạng Email không hợp lệ!";
			return false;
		}
		
		// PhoneNumber check
		if(u.getSdt().equals("")) {
			this.messageCheckInputData = "Lỗi: Bỏ trống số điện thoại!";
			return false;
		}
		String regexPN = "^(0[1-9][0-9]{8,9})$";
		boolean isValidPN = Pattern.matches(regexPN, u.getSdt());
		if(!isValidPN) {
			this.messageCheckInputData = "Lỗi: Số điện thoại không hợp lệ!";
			return false;
		}
		if(!checkPhoneNumberAlreadyExists(u.getSdt())) {
			this.messageCheckInputData = "Lỗi: Số điện thoại đã tồn tại";
			return false;
		}
		
		// Address check
				if(u.getDiachi().equals("")) {
					this.messageCheckInputData = "Lỗi: Bỏ trống địa chỉ!";
					return false;
				}
				String regexAddress = "^.{0,250}$";
				boolean isValidAddress = Pattern.matches(regexAddress, u.getDiachi());
				if(!isValidAddress) {
					this.messageCheckInputData = "Lỗi: Địa chỉ quá dài !";
					return false;
				} 
				
		// Pass check
			if(u.getMatkhau().equals("")) {
				this.messageCheckInputData = "Lỗi: Mật khẩu phải từ 9-50 ký tự. Có it nhất 1 số , 1 chữ cái viết hoa, 1 ký tự đặc biệt!";
				return false;
			}
			String regexPass = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[a-zA-Z]).{9,50}$";
			boolean isValidPass = Pattern.matches(regexPass, u.getMatkhau());
			if(!isValidPass) {
				this.messageCheckInputData = "Lỗi: Mật khẩu phải từ 9-50 ký tự. Có it nhất 1 số , 1 chữ cái viết hoa, 1 ký tự đặc biệt!";
				return false;
			}		
				
		
		// Image check
					if(u.getHinh() == "") {
						this.messageCheckInputData = "Lỗi: Chưa chọn hình!";
						return false;
					}
		return true;
	}
	
	
	public boolean EmailCheckRegex(String email) {
		if(email.equals("")) {
			this.messageCheckInputData = "Lỗi: Không được bỏ trống Email!";
			return false;
		}
		String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|gov|edu|vn|us|uk|au|ca)$";
		Pattern EMAIL_PATTERN = Pattern.compile(regexEmail);
		System.out.println("Email: "+email);
		if(!(EMAIL_PATTERN.matcher(email).matches()) || email.length() > 50) {
			this.messageCheckInputData = "Lỗi: Định dạng Email không hợp lệ!";
			return false;
		}
		return true;
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
	
	public boolean checkSelfDestruct(int id) {
		NguoiDung uLocal = sessionService.getSessionAttribute("user");
		System.out.println("session id: "+uLocal.getMand());
		if(uLocal.getMand() == id) {
			this.messageCheckInputData = "Lỗi: Bạn không thể xoá chính mình!";
			return false;
		}
		
		
		return true;
	}
	
	public boolean checkSessionAdminUpdateToCusomer(NguoiDung u) {
		// when update me admin to customer
		NguoiDung x = new NguoiDung(sessionService.getSessionAttribute("user"));
		if(u.getMand() == x.getMand() && u.isVaitro() != x.isVaitro()) {
			this.messageCheckInputData = "Lỗi: Bạn không thể chuyển mình thành khách hàng!";
			return false;
		}
		
		
		return true;
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
	
//	public boolean handleFileUpload(MultipartFile file) {
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
//	        return true;
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}

}
