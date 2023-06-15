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
	
//	Tìm kiếm theo khoảng giá gốc
	Page<SanPham> findBygiaBetween(double minPrice, double maxPrice, Pageable pageable);

//	Lọc sản phẩm giảm giá lớn hơn 0%
	@Query("SELECT o FROM SanPham o WHERE o.giamgia.giamgia > ?1")
	Page<SanPham> findAllgiamgia(Float keywordss, Pageable pageable);
	
//	Tìm kiếm theo giá đã giảm của sản phẩm
	@Query("SELECT o FROM SanPham o WHERE (o.gia-o.gia*giamgia.giamgia/100) BETWEEN ?1 AND ?2")
	Page<SanPham> findAllGiaDaGiam(double minPrice, double maxPrice, Pageable pageable);
}
