package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.ChiTietDonHang;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.SanPhamSize;



public interface ChiTietDonHangDAO extends JpaRepository<ChiTietDonHang, Integer>{
//	@Query("select ct from ChiTietDonHang ct where ct.donhang.madh Like ?1")
//	List<ChiTietDonHang> findByMaDH(Integer madh);
	

}
