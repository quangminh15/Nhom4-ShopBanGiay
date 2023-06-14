package com.fpoly.ShopBanGiay.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.ShopBanGiay.model.DanhMuc;
import com.fpoly.ShopBanGiay.model.SanPham;

public interface DanhMucDAO extends JpaRepository<DanhMuc, Integer> {
//	Tìm kiếm theo tên danh mục
	Page<DanhMuc> findAllBytendmLike(String keywords, Pageable pageable);
}
