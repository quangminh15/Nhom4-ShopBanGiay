package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.SanPhamSize;

public interface SanPhamSizeDAO extends JpaRepository<SanPhamSize, Integer>{

	
	
	
	
	@Query("select z from SanPhamSize z where z.chitietdonhang.donhang.madh = ?1")
	List<SanPhamSize> findByMaDH(Integer madh);
}
