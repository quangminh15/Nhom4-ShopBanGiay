package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.SanPhamSize;

public interface SanPhamSizeDAO extends JpaRepository<SanPhamSize, Integer>{
//	
	@Query("select z from SanPhamSize z where z.sanpham.masp like ?1")
	List<SanPhamSize> findByMaSP(Integer madh);
	
//	Tìm theo tên sản phẩm 
	@Query("SELECT o FROM SanPhamSize o WHERE o.sanpham.tensp like ?1")
	Page<SanPhamSize> findAllByTenSP(String keywordss, Pageable pageable);
}
