package com.fpoly.ShopBanGiay.model;


import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable{
	@Id
	@NotNull(message = "{NotNull.SanPham.MaSP}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MaSP;
	
	@NotBlank(message = "{NotBlank.SanPham.TenSP}")
	private String TenSP;
	
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh1}")
	private String HinhAnh1;
	
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh2}")
	private String HinhAnh2;
	
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh3}")
	private String HinhAnh3;
	
	@NotNull(message = "{NotNull.SanPham.Loai}")
	private Boolean Loai;
	
	@NotBlank(message = "{NotBlank.SanPham.Gia}")
	private Float Gia;
	
	@NotBlank(message = "{NotBlank.SanPham.MoTa}")
	private String MoTa;
	
	@NotBlank(message = "{NotBlank.SanPham.TrangThai}")
	private boolean TrangThai;
	
	@NotNull(message = "{NotNull.SanPham.MaDM}")
	private int MaDM;
	
	@NotNull(message = "{NotNull.SanPham.MaNCC}")
	private int MaNCC;
	
	private int MaGiamGia;
	
	@OneToOne
	@JoinColumn(name = "MaSP")
    YeuThich yeuthich;
	
	@OneToMany(mappedBy = "sanpham")
    List<SanPhamSize> sanphamsize;
	
	@ManyToOne
    @JoinColumn(name = "MaDM")
    DanhMuc danhmuc;
	
	@ManyToOne
    @JoinColumn(name = "MaNCC")
    NhaCungCap nhacungcap;
	
	@ManyToOne
    @JoinColumn(name = "MaGiamGia")
    GiamGia giamgia;
}