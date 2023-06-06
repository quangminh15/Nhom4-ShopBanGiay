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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanPhamSize")
public class SanPhamSize  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "{NotNull.SanPhamSize.MaSPS}")
	@Column(name = "masps")
	private int MaSPS;
	
	@NotNull(message = "{NotNull.SanPhamSize.MaSP}")
	@Column(name = "masp")
	private int MaSP;
	
	@Column(name = "masize")
	@NotNull(message = "{NotNull.SanPhamSize.MaSize}")
	private int MaSize;
	
	@Column(name = "soluong")
	@NotNull(message = "{NotNull.SanPhamSize.SoLuong}")
	private int SoLuong;
	
	@ManyToOne
    @JoinColumn(name = "masize")
    Size size;
	
	@ManyToOne
    @JoinColumn(name = "masp")
    SanPham sanpham;
	
	@ManyToOne
    @JoinColumn(name = "masps")
    ChiTietDonHang chitietdonhang;
	
//	@OneToOne
//	@JoinColumn(name = "masps")
//    GioHang giohang;
}
