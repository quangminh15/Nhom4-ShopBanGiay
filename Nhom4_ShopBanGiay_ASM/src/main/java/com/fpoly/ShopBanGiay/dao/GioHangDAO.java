package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.fpoly.ShopBanGiay.model.GioHang;

import jakarta.transaction.Transactional;

public interface GioHangDAO extends JpaRepository<GioHang, Integer>{
	
	@Query ("select gh from GioHang gh where gh.nguoidung.mand like ?1")
	List<GioHang> findGioHangByMaND(Integer mand);
	
	 @Query(value = " insert into GioHang(mand,masps,soluong) values (:mand,:masps,:soluong)", nativeQuery = true)
	    @Modifying
	    @Transactional
	    void insertCart(Integer mand,Integer masps,Integer soluong);
}
