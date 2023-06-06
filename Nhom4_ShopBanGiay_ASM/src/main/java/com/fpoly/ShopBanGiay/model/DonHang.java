package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DonHang")
public class DonHang implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madh")
	Integer maDH;

	@Column(name = "ngaytao")
	java.util.Date ngayTao = new java.util.Date();

	@Column(name = "tongtien")
	Double tongTien;

	@Column(name = "diachigiaohang")
	String diaChiGiaoHang;

	@Column(name = "nguoinhan")
	String nguoiNhan;

	@Column(name = "sdtnhanhang")
	String sdtNhanHang;

	@Column(name = "trangthai")
	String trangThai;

	@ManyToOne
	@JoinColumn(name = "mand")
	NguoiDung nguoidung;
	
	@OneToOne(mappedBy = "donhang")
    ThanhToan thanhtoan;
	
	@OneToMany(mappedBy = "madh")
    List<ChiTietDonHang> chitietdonhang;
	
	
}
