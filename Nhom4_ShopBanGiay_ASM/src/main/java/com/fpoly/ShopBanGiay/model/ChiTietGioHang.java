package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
public class ChiTietGioHang implements Serializable{
	

	@ManyToOne
	@JoinColumn(name = "MaGH")
    GioHang gioHang;
	
	
	@ManyToOne
	@JoinColumn(name = "MaSPS")
    SanPhamSize sanphamsize;
	
	
	@Column(name = "SoLuong")
	Integer soLuong;
	 
	
	
}
