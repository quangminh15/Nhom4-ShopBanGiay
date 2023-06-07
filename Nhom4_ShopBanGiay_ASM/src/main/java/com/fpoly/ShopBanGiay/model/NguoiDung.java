package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nguoidung")
public class NguoiDung  implements Serializable{
	@Id
	@NotNull(message = "{NotNull.NguoiDung.MaND}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nd")
	int mand;
		
	@NotBlank(message = "{NotBlank.NguoiDung.MatKhau}")
	@Column(name = "mat_khau")
	String matkhau;
	
	@NotBlank(message = "{NotBlank.NguoiDung.HoTen}")
	@Column(name = "ho_ten")
	String hoten;
	
	@NotBlank(message = "{NotBlank.NguoiDung.DiaChi}")
	@Column(name = "dia_chi")
	String diachi ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.SDT}")
	@Column(name = "sdt")
	String sdt ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.Email}")
	@Email(message = "{Email.NguoiDung.Email}")
	@Column(name = "email")
	String email ;
	
	@NotNull(message = "{NotNull.NguoiDung.TrangThai}")
	@Column(name = "trang_thai")
	boolean trangthai;
	
	@NotBlank(message = "{NotBlank.NguoiDung.Hinh}")
	@Column(name = "hinh")
	String hinh ;
	
	@NotNull(message = "{NotBlank.NguoiDung.VaiTro}")
	@Column(name = "vai_tro")
	boolean vaitro ;
	
	@OneToMany(mappedBy = "nguoidung")
    List<YeuThich> yeuthich;
	
	@OneToMany(mappedBy = "nguoidung")
    List<GioHang> giohang;
	
	@OneToMany(mappedBy = "nguoidung")
    List<DonHang> donhang;
}
