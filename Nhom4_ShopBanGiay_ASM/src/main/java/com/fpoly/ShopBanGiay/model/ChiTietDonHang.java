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
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="chitietdonhang")
public class ChiTietDonHang {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ctdh")
    Integer mactdh;

	@Column(name = "so_luong")
	Integer soluong;
	
	@ManyToOne
    @JoinColumn(name = "ma_dh")
    DonHang donhang;
	
	@ManyToOne
	@JoinColumn(name="ma_sps")
    SanPhamSize sanphamsize;
	
	@Transient
	public Double getSubtotal() {
		 double total=0.0;
		try {
		  total = this.getSanphamsize().getSanpham().getGiamgiasp() * soluong;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
	}
}
