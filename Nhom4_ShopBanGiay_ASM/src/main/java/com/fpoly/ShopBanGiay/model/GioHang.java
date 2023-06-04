package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="GioHang")
public class GioHang implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaGH")
    Integer maGH;
	
	@Column(name = "SoLuong")
	Integer soLuong;
	 
	@OneToOne
	@JoinColumn(name = "MaSPS")
    SanPhamSize sanphamsize;
	
	@ManyToOne
    @JoinColumn(name = "MaND")
    NguoiDung nguoidung;
}
