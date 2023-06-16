package com.fpoly.ShopBanGiay.service;

import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.ThanhToan;

public interface ShoppingCartService {
	Integer addToCart(Integer masps, Integer soluong, NguoiDung nguoiDung);
	
	Double updateQuty(Integer masps, Integer soluong, NguoiDung nguoidung) ;
	
	DonHang addOrder(NguoiDung nguoidung,String diachi,String nguoinhan,String sdt,Double tongtien);
	
	ThanhToan addPayment(DonHang donhang);
	
	void addCartItemsToOderDetails(NguoiDung nguoidung, DonHang donhang);
	
	void remove(Integer id);
	
	void removeAll(Integer mand);
	
	void cancelOrder(Integer mand);
}
