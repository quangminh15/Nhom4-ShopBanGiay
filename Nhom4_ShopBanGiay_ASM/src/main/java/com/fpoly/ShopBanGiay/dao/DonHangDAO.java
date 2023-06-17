package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;

public interface DonHangDAO extends JpaRepository<DonHang, Integer>{
	
	public DonHang findByNguoidungAndTrangthai(NguoiDung nguoidung,String trangthai);
	
	@Query ("Update DonHang dh set dh.trangthai = ?1 where dh.madh = ?2 ")
	@Modifying
	public void updateStatus(String trangthai,Integer mansp);
	
	Page<DonHang> findByNguoidung(NguoiDung nguoidung, Pageable pageable);
	
	Page<DonHang> findByTrangthai(String trangthai,Pageable pageable);
}
