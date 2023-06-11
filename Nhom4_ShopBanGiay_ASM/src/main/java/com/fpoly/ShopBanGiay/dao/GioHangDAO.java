package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPhamSize;



public interface GioHangDAO extends JpaRepository<GioHang, Integer>{
	
	public List<GioHang> findByNguoidung(NguoiDung nguoidung);
	
	public GioHang findByNguoidungAndSanphamsize(NguoiDung nguoidung,SanPhamSize sanphamsize);
	
	@Query ("select gh from GioHang gh where gh.nguoidung.mand like ?1")
	List<GioHang> findGioHangByMaND(Integer mand);
	
//	@Query ("select sum(gh.soluong*gh.sanphamsize.sanpham.gia) from GioHang gh where gh.nguoidung.mand = ?1 ")
//	Integer tongTien(Integer mand);
	
	@Query ("Update GioHang gh set gh.soluong = ?1 where gh.sanphamsize.masps = ?2 and gh.nguoidung.mand = ?3 ")
	@Modifying
	public void updateQty(Integer soluong,Integer masps,Integer mand);

	
	
//	 @Query(value = " insert into GioHang(mand,masps,soluong) values (:mand,:masps,:soluong)", nativeQuery = true)
//	    @Modifying
//	    @Transactional
//	    void insertCart(Integer mand,Integer masps,Integer soluong);
}
