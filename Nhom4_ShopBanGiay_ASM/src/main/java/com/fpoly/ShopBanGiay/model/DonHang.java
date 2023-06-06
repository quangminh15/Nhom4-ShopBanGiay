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

	@ManyToOne
	@JoinColumn(name = "MaND")
	NguoiDung nguoidung;
	
	@OneToOne
	@JoinColumn(name = "MaDH")
    ThanhToan thanhtoan;
	
	@OneToMany(mappedBy = "MaDH")
    List<ChiTietDonHang> chitietdonhang;
	
	
}
