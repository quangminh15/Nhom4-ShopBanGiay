package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.SanPhamSize;

public interface SanPhamSizeDAO extends JpaRepository<SanPhamSize, Integer>{

	
	
	@Query("select z from SanPhamSize z where z.sanpham.masp like ?1")
	List<SanPhamSize> findByMaSP(Integer madh);
	

//	
//	@Query ("select sum(z.giohang.soluong*z.sanpham.gia) from SanPhamSize z where z.giohang.nguoidung.mand like ?1")
//	 Integer tongTien(Integer mand);
}
