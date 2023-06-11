package com.fpoly.ShopBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPhamSize;

@Service
public class ShopingCartServiceImp implements ShoppingCartService {

	@Autowired
	private GioHangDAO ghDAO;
	
	@Autowired
	private SanPhamSizeDAO spsDAO;
	
	public List<GioHang> listGioHang(NguoiDung nguoidung) {
		return ghDAO.findByNguoidung(nguoidung);
	}
	
	@Override
	public Integer addToCart(Integer masps, Integer soluong, NguoiDung nguoiDung) {
		
		Integer addQty = soluong;
		
		SanPhamSize sps = spsDAO.findById(masps).get();
		
		GioHang cart = ghDAO.findByNguoidungAndSanphamsize(nguoiDung, sps);
		
		cart = new GioHang();
		cart.setNguoidung(nguoiDung);
		cart.setSanphamsize(sps);
		cart.setSoluong(soluong);
		
		
		ghDAO.save(cart);
		
		
		return addQty;
	}
}
