package com.fpoly.ShopBanGiay.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="ChiTietDonHang")
public class ChiTietDonHang {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mactdh")
    Integer maCTDH;

	@Column(name = "soluong")
	Integer soLuong;
	
	@ManyToOne
    @JoinColumn(name = "madh")
    DonHang donhang;
	
	@OneToMany(mappedBy = "masps")
    List<SanPhamSize> sanphamsize;
}
