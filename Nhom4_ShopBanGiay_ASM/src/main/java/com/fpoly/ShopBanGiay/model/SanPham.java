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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_sp")
	private Integer masp;
	
	@Column(name = "ten_sp")
	@NotBlank(message = "{NotBlank.SanPham.tensp}")
	@Size( max = 50 , message = "{Size.SanPham.tensp}")
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
	@NotNull(message = "{NotNull.SanPham.gia}")
	@Min(value = 0, message = "{Min.SanPham.gia}")
//	@Max(value = 5,message = "{Max.SanPham.gia}")
	private Float gia;
	
	@Column(name = "mo_ta")
	@NotBlank(message = "{NotBlank.SanPham.mota}")
	@Size( max = 250 , message = "{Size.SanPham.mota}")
	private String mota;
	
	@Column(name = "trang_thai")
	@NotNull(message = "{NotNull.SanPham.trangthai}")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "sanpham")
	List<YeuThich> yeuthich;
	
	@OneToMany(mappedBy = "sanpham")
    List<SanPhamSize> sanphamsize;
	
//	@NotNull(message = "{NotNull.SanPham.danhmuc}")
//	@NotNull(message = "Chưa chọn danh mục")
	@ManyToOne
    @JoinColumn(name = "ma_dm")
    DanhMuc danhmuc;
	
	@NotNull(message = "{NotNull.SanPham.nhacungcap}")
	@ManyToOne
    @JoinColumn(name = "ma_ncc")
    NhaCungCap nhacungcap;
	
	@NotNull(message = "{NotNull.SanPham.giamgia}")
	@ManyToOne
    @JoinColumn(name = "ma_giam_gia")
    GiamGia giamgia;
	
	@Transient
	public Double getGiamgiasp() {
		 double giaKhuyenMai=0.0;
		try {
			giaKhuyenMai = this.getGia() - this.getGia()* giamgia.getGiamgia()/100;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return giaKhuyenMai;
	}
}