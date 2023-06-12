package com.fpoly.ShopBanGiay.service;

import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;

public interface ShoppingCartService {
	Integer addToCart(Integer masps, Integer soluong, NguoiDung nguoiDung);
	
	Double updateQuty(Integer masps, Integer soluong, NguoiDung nguoidung) ;
	
	void addPayment(String method);
	
	DonHang addOrder(NguoiDung nguoidung,String diachi,String nguoinhan,String sdt,Double tongtien);
	
	void addCartItemsToOderDetails(NguoiDung nguoidung, DonHang donhang);
	
	void remove(Integer id);
	
	void removeAll(Integer mand);
}
