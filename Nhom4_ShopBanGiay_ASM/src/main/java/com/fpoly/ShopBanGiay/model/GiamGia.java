package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="giamgia")
public class GiamGia  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_giam_gia")
	Integer magiamgia;
	@Column(name = "ten_giam_gia")
	String tengiamgia;
	@Column(name = "giam_gia")
	Float giamgia;
	@Column(name = "ngay_tao")
	String ngaytao;
	@Column(name = "ngay_ket_thuc")
	String ngayketthuc;
	@Column(name = "mo_ta")
	String mota;
	
	@OneToMany(mappedBy = "giamgia")
    List<SanPham> sanpham;
}
