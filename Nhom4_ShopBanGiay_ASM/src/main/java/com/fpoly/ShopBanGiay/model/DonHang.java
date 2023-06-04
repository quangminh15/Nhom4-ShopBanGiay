package com.fpoly.ShopBanGiay.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="DonHang")
public class DonHang {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDH")
    Integer maDH;
	
	@Column(name = "NgayTao")
    java.util.Date ngayTao = new java.util.Date();
	
	 @Column(name = "TongTien")
	 Double tongTien;
	 
	 @Column(name = "DiaChiGiaoHang")
	 String diaChiGiaoHang;
	 
	 @Column(name = "NguoiNhan")
	 String nguoiNhan;
	 
	 @Column(name = "SdtNhanHang")
	 String sdtNhanHang;
	 
	 @Column(name = "TrangThai")
	 String trangThai;
}
