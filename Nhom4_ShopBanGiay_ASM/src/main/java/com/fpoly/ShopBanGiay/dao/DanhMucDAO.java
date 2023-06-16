package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.DanhMuc;

public interface DanhMucDAO extends JpaRepository<DanhMuc, Integer> {
//	Tìm kiếm theo tên danh mục
	Page<DanhMuc> findAllBytendmLike(String keywords, Pageable pageable);
	
//	Hiển thị danh mục có trạng thái đang hoạt động lên trang user
	@Query("SELECT o FROM DanhMuc o WHERE o.trangthai = true")
	List<DanhMuc> findAllBytendmLike();
}
