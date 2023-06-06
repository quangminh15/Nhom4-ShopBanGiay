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

@Entity
@Table(name="chitietgiohang")
public class ChiTietGioHang implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_ctgh")
	int mactgh;
	
	@ManyToOne
	@JoinColumn(name = "ma_gh")
    GioHang giohang;
	
	
	@ManyToOne
	@JoinColumn(name = "ma_sps")
    SanPhamSize sanphamsize;
	
	
	@Column(name = "so_luong")
	Integer soluong;
	 
	
	
}
