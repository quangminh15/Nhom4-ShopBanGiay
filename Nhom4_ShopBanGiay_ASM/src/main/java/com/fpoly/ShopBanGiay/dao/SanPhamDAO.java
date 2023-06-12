package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.SanPham;



public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{
//	lọc theo tên danh mục
	@Query("SELECT o FROM SanPham o WHERE o.danhmuc.tendm LIKE ?1")
	List<SanPham> findByTenDM(String keywords);
	
//	Lọc theo mã danh mục 
	@Query("SELECT o FROM SanPham o WHERE o.danhmuc.madm LIKE ?1")
	Page<SanPham> findAllBytendm(Integer keywordss, Pageable pageable);
	
//	Lọc theo loại giày
	@Query("SELECT o FROM SanPham o WHERE o.loai = ?1")
	Page<SanPham> findAllByLoai(Boolean keywordss, Pageable pageable);
	
//	Tìm kiếm theo tên sản phẩm
	Page<SanPham> findAllBytenspLike(String keywords, Pageable pageable);
	
//	Tìm kiếm theo khoảng giá
	Page<SanPham> findBygiaBetween(double minPrice, double maxPrice, Pageable pageable);
}
