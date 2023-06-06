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
	@Column(name = "MaSPS")
	private int MaSPS;
	
	@NotNull(message = "{NotNull.SanPhamSize.MaSP}")
	@Column(name = "MaSP")
	private int MaSP;
	
	@Column(name = "MaSize")
	@NotNull(message = "{NotNull.SanPhamSize.MaSize}")
	private int MaSize;
	
	@Column(name = "SoLuong")
	@NotNull(message = "{NotNull.SanPhamSize.SoLuong}")
	private int SoLuong;
	
	@ManyToOne
    @JoinColumn(name = "MaSize")
    Size size;
	
	@ManyToOne
    @JoinColumn(name = "MaSP")
    SanPham sanpham;
	
	@ManyToOne
    @JoinColumn(name = "MaSPS")
    ChiTietDonHang chitietdonhang;
	
	@OneToOne
	@JoinColumn(name = "MaSPS")
    GioHang giohang;
}
