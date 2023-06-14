package com.fpoly.ShopBanGiay.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.Size;

public interface SizeDAO extends JpaRepository<Size, Integer> {
	Page<Size> findAllBysizegiayLike(String keywords, Pageable pageable);
	
//	tìm kiếm theo size giày
//	@Query("SELECT o FROM Size o WHERE o.sizegiay LIKE ?1")
	Page<Size> findAllBysizegiayBetween(double minPrice, double maxPrice, Pageable pageable);

}
