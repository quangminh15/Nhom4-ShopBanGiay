package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "donhang")
public class DonHang implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_dh")
	Integer madh;

	@Column(name = "ngay_tao")
	 String ngaytao;

	@Column(name = "tong_tien")
	Double tongtien;

	@Column(name = "dia_chigiaohang")
	String diachigiaohang;

	@Column(name = "nguoi_nhan")
	String nguoinhan;

	@Column(name = "sdt_nhanhang")
	String sdtnhanhang;

	@Column(name = "trang_thai")
	String trangthai;

	@ManyToOne
	@JoinColumn(name = "ma_nd")
	NguoiDung nguoidung;
	
	
	@OneToMany(mappedBy = "donhang")
    List<ChiTietDonHang> chitietdonhang;
	
	@OneToOne
	(mappedBy =  "donhang")
    ThanhToan thantoan;
}
