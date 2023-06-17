package com.fpoly.ShopBanGiay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.ShopBanGiay.dao.YeuThichDAO;
import com.fpoly.ShopBanGiay.model.ReportYT;
import com.fpoly.ShopBanGiay.service.ReportService;


@Controller
public class ThongKeYTController {
	@Autowired
	YeuThichDAO ytDao;
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping("/admin/admin_report")
	public String report(Model model) {
		List<ReportYT> list = reportService.getTop3();
		model.addAttribute("items", list);
		return "/admin/admin_reportyt";
	}
}
