package com.fpoly.ShopBanGiay.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

import com.fpoly.ShopBanGiay.dao.KhuyenMaiDAO;
import com.fpoly.ShopBanGiay.model.GiamGia;
import com.fpoly.ShopBanGiay.model.Size;

import jakarta.validation.Valid;

@Controller
public class admin_khuyenmaiController {
	@Autowired
	KhuyenMaiDAO khuyenmaiDao;
	
	String check = "";

	@GetMapping("/admin/admin_khuyenmai")
	public String admin_khuyenmai(Model model, @RequestParam("p") Optional<Integer> p) {
		GiamGia g = new GiamGia();
		model.addAttribute("kms", g);

		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		var giamgia = khuyenmaiDao.findAll(pageable);
		var numberOfPages = giamgia.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("KM", giamgia);
		return "/admin/admin_khuyenmai";
	}

	@GetMapping("/page")
	public String page(Model model, @RequestParam("p") Optional<Integer> p) {
		return this.admin_khuyenmai(model, p);
	}

	@PostMapping("/admin/add_khuyenmai")
	public String save_khuyenmai(@Valid @ModelAttribute("kms") GiamGia giamgia,  BindingResult result, Model model, @RequestParam("p") Optional<Integer> p) {
		// Định dạng ngày đầu vào
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
	    SimpleDateFormat sdm = new SimpleDateFormat("MM");
	    if (result.hasErrors()) {
			model.addAttribute("KM", khuyenmaiDao.findAll());
			Pageable pageable = PageRequest.of(p.orElse(0), 5);
			var giamgia1 = khuyenmaiDao.findAll(pageable);
			var numberOfPages = giamgia1.getTotalPages();
			model.addAttribute("currIndex", p.orElse(0));
			model.addAttribute("numberOfPages", numberOfPages);
			model.addAttribute("KM", giamgia1);
			return "/admin/admin_khuyenmai";
		}else
	     if(giamgia != null) {
			try {
				Date start = sdf.parse(giamgia.getNgaytao());
				Date end = sdf.parse(giamgia.getNgayketthuc());
				
				Date startY = sdy.parse(giamgia.getNgaytao());
				Date endY = sdy.parse(giamgia.getNgayketthuc());
				
				Date startM = sdm.parse(giamgia.getNgaytao());
				Date endM = sdm.parse(giamgia.getNgayketthuc());
				
			    Date today = new Date();
			    
//			    else if(end.compareTo(today) < 0) {
//			    	  model.addAttribute("checkDate", "Ngày kết thúc phải lớn hơn ngày hiện tại!!!!!");
//			    	  model.addAttribute("KM", khuyenmaiDao.findAll());
//			    	  
//			    	  Pageable pageable = PageRequest.of(p.orElse(0), 5);
//						var giamgia1 = khuyenmaiDao.findAll(pageable);
//						var numberOfPages = giamgia1.getTotalPages();
//						model.addAttribute("currIndex", p.orElse(0));
//						model.addAttribute("numberOfPages", numberOfPages);
//						model.addAttribute("KM", giamgia1);
//						return "/admin/admin_khuyenmai";
//			    }else
			    	if(startY.after(endY)) {
			        // Ngày bắt đầu bằng ngày kết thúc
			    	  model.addAttribute("checkDate", "Năm kết thúc đang nhỏ hơn năm bắt đầu!!!!");
			    	  model.addAttribute("KM", khuyenmaiDao.findAll());
			    	  Pageable pageable = PageRequest.of(p.orElse(0), 5);
						var giamgia1 = khuyenmaiDao.findAll(pageable);
						var numberOfPages = giamgia1.getTotalPages();
						model.addAttribute("currIndex", p.orElse(0));
						model.addAttribute("numberOfPages", numberOfPages);
						model.addAttribute("KM", giamgia1);
						return "/admin/admin_khuyenmai";
			    }
			    else if(startM.before(endM)) {
			    		  model.addAttribute("checkDate", "thang ket thuc nho hon thang bat dau");
				    	  model.addAttribute("KM", khuyenmaiDao.findAll());
				    	  Pageable pageable = PageRequest.of(p.orElse(0), 5);
							var giamgia1 = khuyenmaiDao.findAll(pageable);
							var numberOfPages = giamgia1.getTotalPages();
							model.addAttribute("currIndex", p.orElse(0));
							model.addAttribute("numberOfPages", numberOfPages);
							model.addAttribute("KM", giamgia1);
							return "/admin/admin_khuyenmai";
			    	  
			      }else if (start.after(end)) {
				        // Ngày bắt đầu lớn hơn ngày kết thúc
				    	model.addAttribute("checkDate", "Ngày bắt đầu đang lớn hơn ngày kết thúc!!!");
				    	model.addAttribute("KM", khuyenmaiDao.findAll());
				    	Pageable pageable = PageRequest.of(p.orElse(0), 5);
						var giamgia1 = khuyenmaiDao.findAll(pageable);
						var numberOfPages = giamgia1.getTotalPages();
						model.addAttribute("currIndex", p.orElse(0));
						model.addAttribute("numberOfPages", numberOfPages);
						model.addAttribute("KM", giamgia1);
						return "/admin/admin_khuyenmai";
			     }else if(start.equals(end)) {
			    	// Ngày bắt đầu lớn hơn ngày kết thúc
				    	model.addAttribute("checkDate", "Ngày bắt đầu đang bằng ngày kết thúc!!!!!");
				    	model.addAttribute("KM", khuyenmaiDao.findAll());
				    	Pageable pageable = PageRequest.of(p.orElse(0), 5);
						var giamgia1 = khuyenmaiDao.findAll(pageable);
						var numberOfPages = giamgia1.getTotalPages();
						model.addAttribute("currIndex", p.orElse(0));
						model.addAttribute("numberOfPages", numberOfPages);
						model.addAttribute("KM", giamgia1);
						return "/admin/admin_khuyenmai";
			     }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    khuyenmaiDao.save(giamgia);
	    model.addAttribute("message", "Thêm thành công");
	    
			
	    Pageable pageable = PageRequest.of(p.orElse(0), 5);
		var giamgia1 = khuyenmaiDao.findAll(pageable);
		var numberOfPages = giamgia1.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("KM", giamgia1);
		
//		model.addAttribute("KM", khuyenmaiDao.findAll());
		return "/admin/admin_khuyenmai";
	}
	
	@RequestMapping("/admin/update_khuyenmai")
	public String update_khuyenmai(@Valid @ModelAttribute("kms") GiamGia giamgia, BindingResult result, Model model,
			@RequestParam("p") Optional<Integer> p) {
		// Định dạng ngày đầu vào
		// Định dạng ngày đầu vào
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdm = new SimpleDateFormat("MM");
		if (result.hasErrors()) {
			model.addAttribute("KM", khuyenmaiDao.findAll());
			Pageable pageable = PageRequest.of(p.orElse(0), 5);
			var giamgia1 = khuyenmaiDao.findAll(pageable);
			var numberOfPages = giamgia1.getTotalPages();
			model.addAttribute("currIndex", p.orElse(0));
			model.addAttribute("numberOfPages", numberOfPages);
			model.addAttribute("KM", giamgia1);
			return "/admin/admin_khuyenmai";
		}else if(giamgia.getTengiamgia() != null) {
			if(!check(giamgia)) {
				model.addAttribute("checkDate", this.check);
				model.addAttribute("KM", khuyenmaiDao.findAll());

				Pageable pageable = PageRequest.of(p.orElse(0), 5);
				var giamgia1 = khuyenmaiDao.findAll(pageable);
				var numberOfPages = giamgia1.getTotalPages();
				model.addAttribute("currIndex", p.orElse(0));
				model.addAttribute("numberOfPages", numberOfPages);
				model.addAttribute("KM", giamgia1);
				return "/admin/admin_khuyenmai";
			}
		}
		else if (giamgia.getNgayketthuc() != null && giamgia.getNgaytao() != null) {
			try {
				Date start = sdf.parse(giamgia.getNgaytao());
				Date end = sdf.parse(giamgia.getNgayketthuc());

				Date startY = sdy.parse(giamgia.getNgaytao());
				Date endY = sdy.parse(giamgia.getNgayketthuc());

				Date startM = sdm.parse(giamgia.getNgaytao());
				Date endM = sdm.parse(giamgia.getNgayketthuc());

				Date today = new Date();
				
				
					if (end.compareTo(today) == 0) {
					model.addAttribute("checkDate", "Ngay ket thuc nho hon ngay hien tai");
					model.addAttribute("KM", khuyenmaiDao.findAll());

					Pageable pageable = PageRequest.of(p.orElse(0), 5);
					var giamgia1 = khuyenmaiDao.findAll(pageable);
					var numberOfPages = giamgia1.getTotalPages();
					model.addAttribute("currIndex", p.orElse(0));
					model.addAttribute("numberOfPages", numberOfPages);
					model.addAttribute("KM", giamgia1);
					return "/admin/admin_khuyenmai";
				} else if (startY.after(endY)) {
					// Ngày bắt đầu bằng ngày kết thúc
					model.addAttribute("checkDate", "Nam ket thuc nho hon nam bat dau");
					model.addAttribute("KM", khuyenmaiDao.findAll());
					Pageable pageable = PageRequest.of(p.orElse(0), 5);
					var giamgia1 = khuyenmaiDao.findAll(pageable);
					var numberOfPages = giamgia1.getTotalPages();
					model.addAttribute("currIndex", p.orElse(0));
					model.addAttribute("numberOfPages", numberOfPages);
					model.addAttribute("KM", giamgia1);
					return "/admin/admin_khuyenmai";
				} else if (startM.after(endM)) {
					model.addAttribute("checkDate", "Thang ket thuc nho hon thang bat dau");
					model.addAttribute("KM", khuyenmaiDao.findAll());
					Pageable pageable = PageRequest.of(p.orElse(0), 5);
					var giamgia1 = khuyenmaiDao.findAll(pageable);
					var numberOfPages = giamgia1.getTotalPages();
					model.addAttribute("currIndex", p.orElse(0));
					model.addAttribute("numberOfPages", numberOfPages);
					model.addAttribute("KM", giamgia1);
					return "/admin/admin_khuyenmai";
				} else if (start.after(end)) {
					// Ngày bắt đầu lớn hơn ngày kết thúc
					model.addAttribute("checkDate", "Ngày bắt đầu nho hon ngày kết thúc");
					model.addAttribute("KM", khuyenmaiDao.findAll());
					Pageable pageable = PageRequest.of(p.orElse(0), 5);
					var giamgia1 = khuyenmaiDao.findAll(pageable);
					var numberOfPages = giamgia1.getTotalPages();
					model.addAttribute("currIndex", p.orElse(0));
					model.addAttribute("numberOfPages", numberOfPages);
					model.addAttribute("KM", giamgia1);
					return "/admin/admin_khuyenmai";
				} else if (start.equals(end)) {
					// Ngày bắt đầu lớn hơn ngày kết thúc
					model.addAttribute("checkDate", "Ngày bắt đầu bang ngày kết thúc");
					model.addAttribute("KM", khuyenmaiDao.findAll());
					Pageable pageable = PageRequest.of(p.orElse(0), 5);
					var giamgia1 = khuyenmaiDao.findAll(pageable);
					var numberOfPages = giamgia1.getTotalPages();
					model.addAttribute("currIndex", p.orElse(0));
					model.addAttribute("numberOfPages", numberOfPages);
					model.addAttribute("KM", giamgia1);
					return "/admin/admin_khuyenmai";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		khuyenmaiDao.save(giamgia);
		model.addAttribute("message", "Cập nhật thành công");

		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		var giamgia1 = khuyenmaiDao.findAll(pageable);
		var numberOfPages = giamgia1.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("KM", giamgia1);

//		model.addAttribute("KM", khuyenmaiDao.findAll());
		return "/admin/admin_khuyenmai";
	}

	@RequestMapping("/edit_khuyenmai/{magiamgia}")
	public String edit_khuyenmai(Model model, @PathVariable(name = "magiamgia") Integer magiamgia, @RequestParam("p") Optional<Integer> p) {
		Optional<GiamGia> g = khuyenmaiDao.findById(magiamgia);
		if (g.isPresent()) {
			model.addAttribute("kms", g.get());
			model.addAttribute("KM", khuyenmaiDao.findAll());
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<GiamGia> kmss = khuyenmaiDao.findAll(pageable);

		var numberOfPages = kmss.getTotalPages();

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);

		model.addAttribute("KM", kmss);
		return "/admin/admin_khuyenmai";
	}

	@RequestMapping("/delete_khuyenmai/{magiamgia}")
	public String delete_khuyenmai(Model model, @PathVariable(name = "magiamgia") Integer magiamgia,  @RequestParam("p") Optional<Integer> p) {
		GiamGia g = new GiamGia();
		khuyenmaiDao.deleteById(magiamgia);
		model.addAttribute("kms", g);
		model.addAttribute("KM", khuyenmaiDao.findAll());
		model.addAttribute("message", "Xóa thành công");
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		var giamgia1 = khuyenmaiDao.findAll(pageable);
		var numberOfPages = giamgia1.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("KM", giamgia1);
		return "/admin/admin_khuyenmai";
	}

	@PostMapping("/admin_khuyenmai/clear")
	public String clear(@ModelAttribute("khuyenmai") GiamGia khuyenmai) {
		khuyenmai.setMagiamgia(null);
		khuyenmai.setTengiamgia(null);
		khuyenmai.setGiamgia(null);
		khuyenmai.setMota(null);
		khuyenmai.setNgayketthuc(null);
		khuyenmai.setNgaytao(null);
		return "redirect:/admin/admin_khuyenmai";
	}
	
	public boolean checkTenGG(String tengiamgia) {
		List<GiamGia> list = khuyenmaiDao.findByTenGG(tengiamgia);
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean check(GiamGia giamgia) {
		if(!checkTenGG(giamgia.getTengiamgia())) {
			this.check = "Tên Khuyến mãi đã tồn tại vui lòng nhập tên mới";
			return true;
		}
		return false;
	}
}
