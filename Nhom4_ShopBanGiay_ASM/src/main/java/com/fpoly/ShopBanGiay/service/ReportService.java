package com.fpoly.ShopBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.ShopBanGiay.dao.YeuThichDAO;
import com.fpoly.ShopBanGiay.model.ReportYT;

@Service
public class ReportService {
	@Autowired
	private YeuThichDAO ytDao;
	
	public List<ReportYT> getTop3(){
		List<ReportYT> result = ytDao.getReportYT();
		result = result.subList(0, 3);
		return result;
	}
}
