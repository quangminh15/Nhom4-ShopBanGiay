package com.fpoly.ShopBanGiay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.dao.SizeDAO;
import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.Size;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class admin_sanphamsizeController {
	@Autowired
	SessionService session;

	@Autowired
	SanPhamSizeDAO sanphamsizeDAO;

	@Autowired
	SanPhamDAO sanphamDAO;

	@Autowired
	SizeDAO sizeDAO;

	@GetMapping("/admin/admin_sanphamsize")
	public String admin_danhmucsp(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		SanPhamSize sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize", sanphamsize);

		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by(Direction.DESC, field.orElse("masps")).ascending());
		var list = sanphamsizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", list);

		return "/admin/admin_sanphamsize";
	}

	@GetMapping("/admin/admin_sanphamsize/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {
		return this.admin_danhmucsp(model, p, field);
	}

	@ModelAttribute("sanphams")
	public List<SanPham> getsanpham() {
		List<SanPham> sanpham = sanphamDAO.findAll();
		return sanpham;
	}

	@ModelAttribute("sizes")
	public List<Size> getsizegiayy() {
		List<Size> sizee = sizeDAO.findAll();
		return sizee;
	}

	@RequestMapping("/admin/admin_sanphamsize/create")
	public String add(@Valid @ModelAttribute("sanphamsize") SanPhamSize sanphamsize, BindingResult result, Model model,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<SanPhamSize> sanphamsizes = sanphamsizeDAO.findAll();
			model.addAttribute("sanphamsizes", sanphamsizes);
			return "/admin/admin_sanphamsize";
		}
		try {
			sanphamsizeDAO.save(sanphamsize);
			model.addAttribute("success", "Thêm thành công!");
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("error", "Thêm thất bại do sản phẩm và size này đã tồn tại!");
		} catch (Exception e) {
			model.addAttribute("error", "Thêm thất bại!");
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by("masps").ascending());
		var list = sanphamsizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", list);
		sanphamsize = new SanPhamSize();

		model.addAttribute("sanphamsize", sanphamsize);

		return "/admin/admin_sanphamsize";
	}

	@RequestMapping("/admin/admin_sanphamsize/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by("masps").ascending());
		var list = sanphamsizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", list);
		SanPhamSize sanphamsize = sanphamsizeDAO.findById(id).orElse(null);
//		sanpham.getHinhanh1();
//		sanpham.getHinhanh2();
//		sanpham.getHinhanh3();
//		sanpham.getDanhmuc().getMadm();
//		sanpham.getNhacungcap().getMancc();
//		sanpham.getGiamgia().getMagiamgia();
		model.addAttribute("sanphamsize", sanphamsize);
		return "/admin/admin_sanphamsize";
	}

	@RequestMapping("/admin/admin_sanphamsize/delete/{masps}")
	public String remove(Model model, @PathVariable("masps") Integer id, @RequestParam("p") Optional<Integer> p) {
		try {
			sanphamsizeDAO.deleteById(id);
			model.addAttribute("success", "Xóa thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Xóa thất bại: " + e);
			model.addAttribute("error", "Xóa thất bại!");
		}
		
		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by("masps").ascending());
		var list = sanphamsizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", list);
		SanPhamSize sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize", sanphamsize);
		return "/admin/admin_sanphamsize";
	}

	@RequestMapping("/admin/admin_sanphamsize/update")
	public String update(Model model, @Valid @ModelAttribute("sanphamsize") SanPhamSize sanphamsize, BindingResult result,
			@RequestParam("p") Optional<Integer> p) {
		if (result.hasErrors()) {
			List<SanPhamSize> sanphamsizes = sanphamsizeDAO.findAll();
			model.addAttribute("sanphamsizes", sanphamsizes);
			return "/admin/admin_sanphamsize";
		}
		try {
			sanphamsizeDAO.save(sanphamsize);
			model.addAttribute("success", "Cập nhật thành công!");
		} catch (Exception e) {
			model.addAttribute("error", "Cập nhật thất bại!");
		}
		
		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by("masps").ascending());
		var list = sanphamsizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", list);
		sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize", sanphamsize);
		return "/admin/admin_sanphamsize";
	}

	@RequestMapping("/admin/admin_sanphamsize/clear")
	public String update(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by("masps").ascending());
		var list = sanphamsizeDAO.findAll(pageable);
		var numberOfPages = list.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", list);
		SanPhamSize sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize", sanphamsize);
		return "/admin/admin_sanphamsize";
	}
	
	@RequestMapping("/admin/admin_sanphamsize/timkiem")
	public String getTimKiem(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("keywords") Optional<String> kw) {
		SanPhamSize sanphamsize = new SanPhamSize();
		model.addAttribute("sanphamsize", sanphamsize);
		
		String kwords = kw.orElse("");
		session.getSessionAttribute("keywords");
		session.setSessionAttribute("keywords", kwords);

		Pageable pageable = PageRequest.of(p.orElse(0), 20, Sort.by("masps").ascending());
		Page<SanPhamSize> sanphamsizes = sanphamsizeDAO.findAllByTenSP("%" + kwords + "%", pageable);
		if(sanphamsizes.isEmpty()) {
			model.addAttribute("message", "Không có sản phẩm mà bạn muốn tìm kiếm");
			sanphamsizes = sanphamsizeDAO.findAll(pageable);
		}
		var numberOfPages = sanphamsizes.getTotalPages();
		

		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sanphamsizes", sanphamsizes);
		return "/admin/admin_sanphamsize";
	}
}
