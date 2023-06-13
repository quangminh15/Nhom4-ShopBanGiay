package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;

public interface DonHangDAO extends JpaRepository<DonHang, Integer>{
	
	public DonHang findByNguoidungAndTrangthai(NguoiDung nguoidung,String trangthai);
	
	List<DonHang> findByNguoidung(NguoiDung nguoidung);
}
