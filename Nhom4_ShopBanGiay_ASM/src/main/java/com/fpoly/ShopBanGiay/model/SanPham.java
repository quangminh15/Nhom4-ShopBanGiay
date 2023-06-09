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
@Table(name = "sanpham")
public class SanPham implements Serializable{
	@Id
	@NotNull(message = "{NotNull.SanPham.masp}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_sp")
	private int masp;
	
	@Column(name = "ten_sp")
	@NotBlank(message = "{NotBlank.SanPham.tensp}")
	private String tensp;
	
	@Column(name = "hinh_anh1")
	@NotBlank(message = "{NotBlank.SanPham.hinhanh1}")
	private String hinhanh1;
	
	@Column(name = "hinh_anh2")
	@NotBlank(message = "{NotBlank.SanPham.hinhanh2}")
	private String hinhanh2;
	
	@Column(name = "hinh_anh3")
	@NotBlank(message = "{NotBlank.SanPham.hinhanh3}")
	private String hinhanh3;
	
	@Column(name = "loai")
	@NotNull(message = "{NotNull.SanPham.loai}")
	private Boolean loai;
	
	@Column(name = "gia")
	@NotBlank(message = "{NotBlank.SanPham.gia}")
	private Float gia;
	
	@Column(name = "mo_ta")
	@NotBlank(message = "{NotBlank.SanPham.mota}")
	private String mota;
	
	@Column(name = "trang_thai")
	@NotBlank(message = "{NotBlank.SanPham.trangthai}")
	private boolean trangthai;
	
	@OneToMany(mappedBy = "sanpham")
	List<YeuThich> yeuthich;
	
	@OneToMany(mappedBy = "sanpham")
    List<SanPhamSize> sanphamsize;
	
	@ManyToOne
    @JoinColumn(name = "ma_dm")
    DanhMuc danhmuc;
	
	@ManyToOne
    @JoinColumn(name = "ma_ncc")
    NhaCungCap nhacungcap;
	
	@ManyToOne
    @JoinColumn(name = "ma_giam_gia")
    GiamGia giamgia;
}