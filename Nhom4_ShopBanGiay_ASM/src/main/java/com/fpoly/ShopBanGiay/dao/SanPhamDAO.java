package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;


public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{
//	@Query("SELECT o FROM SanPham o WHERE o.ma_dm LIKE ?1")
//	Page<SanPham> findByMaDM(String keywords, Pageable pageable);
	
	Page<SanPham> findAllBydanhmuc(String keywords, Pageable pageable);
	Page<SanPham> findByDanhmuc(DanhMuc danhmuc, Pageable pageable);
}
