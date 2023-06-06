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
@Table(name = "NguoiDung")
public class NguoiDung  implements Serializable{
	@Id
	@NotNull(message = "{NotNull.NguoiDung.MaND}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaND")
	int MaND;
		
	@NotBlank(message = "{NotBlank.NguoiDung.MatKhau}")
	@Column(name = "MatKhau")
	String MatKhau;
	
	@NotBlank(message = "{NotBlank.NguoiDung.HoTen}")
	@Column(name = "HoTen")
	String HoTen;
	
	@NotBlank(message = "{NotBlank.NguoiDung.DiaChi}")
	@Column(name = "DiaChi")
	String DiaChi ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.SDT}")
	@Column(name = "SDT")
	String SDT ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.Email}")
	@Email(message = "{Email.NguoiDung.Email}")
	@Column(name = "Email")
	String Email ;
	
	@NotNull(message = "{NotNull.NguoiDung.TrangThai}")
	@Column(name = "TrangThai")
	boolean TrangThai;
	
	@NotBlank(message = "{NotBlank.NguoiDung.Hinh}")
	@Column(name = "Hinh")
	String Hinh ;
	
	@NotNull(message = "{NotBlank.NguoiDung.VaiTro}")
	@Column(name = "VaiTro")
	boolean VaiTro ;
	
	@OneToMany(mappedBy = "nguoidung")
    List<YeuThich> yeuthich;
	
	@OneToMany(mappedBy = "nguoidung")
    List<GioHang> giohang;
	
	@OneToMany(mappedBy = "nguoidung")
    List<DonHang> donhang;
}
