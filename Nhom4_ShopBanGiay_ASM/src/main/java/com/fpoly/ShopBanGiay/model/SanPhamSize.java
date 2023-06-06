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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sanphamsize")
public class SanPhamSize  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "{NotNull.SanPhamSize.MaSPS}")
	@Column(name = "ma_sps")
	private int masps;
	
//	@NotNull(message = "{NotNull.SanPhamSize.MaSP}")
//	@Column(name = "masp")
//	private int MaSP;
//	
//	@Column(name = "masize")
//	@NotNull(message = "{NotNull.SanPhamSize.MaSize}")
//	private int MaSize;
	
	@Column(name = "so_luong")
	@NotNull(message = "{NotNull.SanPhamSize.SoLuong}")
	private int soluong;
	
	@ManyToOne
    @JoinColumn(name = "ma_size")
    Size size;
	
	@ManyToOne
    @JoinColumn(name = "ma_sp")
    SanPham sanpham;
	
	@ManyToOne
    @JoinColumn(name = "ma_spsize")
    ChiTietDonHang chitietdonhang;
	
	@OneToMany(mappedBy = "sanphamsize")
	List<ChiTietGioHang> chitietgiohang;
	
//	@OneToOne
//	@JoinColumn(name = "masps")
//    GioHang giohang;
}
