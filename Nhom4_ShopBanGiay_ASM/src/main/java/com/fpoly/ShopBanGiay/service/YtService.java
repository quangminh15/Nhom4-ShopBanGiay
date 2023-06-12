package com.fpoly.ShopBanGiay.service;

import java.time.LocalDate;
import java.util.Date;

import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPham;

public interface YtService {
	Date add(NguoiDung nguoidung, String ngaythich, Integer masp);
}
