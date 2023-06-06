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
	@Column(name = "masp")
	private int MaSP;
	
	@Column(name = "tensp")
	@NotBlank(message = "{NotBlank.SanPham.TenSP}")
	private String TenSP;
	
	@Column(name = "hinhanh1")
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh1}")
	private String HinhAnh1;
	
	@Column(name = "hinhanh2")
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh2}")
	private String HinhAnh2;
	
	@Column(name = "hinhanh3")
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh3}")
	private String HinhAnh3;
	
	@Column(name = "loai")
	@NotNull(message = "{NotNull.SanPham.Loai}")
	private Boolean Loai;
	
	@Column(name = "gia")
	@NotBlank(message = "{NotBlank.SanPham.Gia}")
	private Float Gia;
	
	@Column(name = "mota")
	@NotBlank(message = "{NotBlank.SanPham.MoTa}")
	private String MoTa;
	
	@Column(name = "trangthai")
	@NotBlank(message = "{NotBlank.SanPham.TrangThai}")
	private boolean TrangThai;
	
	@Column(name = "madm")
	@NotNull(message = "{NotNull.SanPham.MaDM}")
	private int MaDM;
	
	@Column(name = "mancc")
	@NotNull(message = "{NotNull.SanPham.MaNCC}")
	private int MaNCC;
	
	@Column(name = "magiamgia")
	private int MaGiamGia;
	
	@OneToMany(mappedBy = "sanpham")
	List<YeuThich> yeuthich;
	
	@OneToMany(mappedBy = "sanpham")
    List<SanPhamSize> sanphamsize;
	
	@ManyToOne
    @JoinColumn(name = "madm")
    DanhMuc danhmuc;
	
	@ManyToOne
    @JoinColumn(name = "mancc")
    NhaCungCap nhacungcap;
	
	@ManyToOne
    @JoinColumn(name = "magiamgia")
    GiamGia giamgia;
}