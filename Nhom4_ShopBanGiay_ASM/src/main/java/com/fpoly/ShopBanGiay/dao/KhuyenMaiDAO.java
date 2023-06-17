package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.GiamGia;

public interface KhuyenMaiDAO extends JpaRepository<GiamGia, Integer> {
	@Query("SELECT o FROM GiamGia o Where o.tengiamgia Like  ?1")
	List<GiamGia> findByTenGG(String tengiamgia);
}
