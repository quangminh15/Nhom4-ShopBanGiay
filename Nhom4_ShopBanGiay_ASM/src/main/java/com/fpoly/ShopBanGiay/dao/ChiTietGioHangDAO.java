package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.ChiTietDonHang;

import com.fpoly.ShopBanGiay.model.SanPhamSize;

public interface ChiTietGioHangDAO extends JpaRepository<ChiTietDonHang, Integer>{
	@Query("Select s.SanPham.TenSP form SanPhamSize s Where s.MaSPS in (select c.SanPhamSize.MaSPS form ChiTietGioHang)")
	List<SanPhamSize> getGioHang();
}
