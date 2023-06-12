package com.fpoly.ShopBanGiay.service;

import com.fpoly.ShopBanGiay.model.NguoiDung;

public interface ShoppingCartService {
	Integer addToCart(Integer masps, Integer soluong, NguoiDung nguoiDung);
	
	Double updateQuty(Integer masps, Integer soluong, NguoiDung nguoidung) ;
}
