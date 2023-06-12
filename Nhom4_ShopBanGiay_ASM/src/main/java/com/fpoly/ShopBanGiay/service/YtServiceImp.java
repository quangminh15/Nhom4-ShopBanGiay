package com.fpoly.ShopBanGiay.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.ShopBanGiay.dao.SanPhamDAO;
import com.fpoly.ShopBanGiay.dao.YeuThichDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.YeuThich;

@Service
public class YtServiceImp implements YtService {

	@Autowired
	YeuThichDAO ytDao;
	
	@Autowired
	SanPhamDAO sp;
	
	
	
	@Override
	public Date add(NguoiDung nguoidung, String ngaythich, Integer masp) {
		@SuppressWarnings("deprecation")
		Date date = new Date(2020, 11, 11);
		SanPham sps = sp.findById(masp).get();
		YeuThich yt = ytDao.findByNguoidungAndSanpham(nguoidung, sps);
		
		yt = new YeuThich();
		yt.setNgaythich(date);
		yt.setNguoidung(nguoidung);
		yt.setSanpham(sps);
		
		ytDao.save(yt);
		return date;
	}

}
