package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
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
	
	@NotNull(message = "{NotNull.NguoiDung.MaND}")
	int MaND;
		
	@NotBlank(message = "{NotBlank.NguoiDung.MatKhau}")
	String MatKhau;
	
	@NotBlank(message = "{NotBlank.NguoiDung.HoTen}")
	String HoTen;
	
	@NotBlank(message = "{NotBlank.NguoiDung.DiaChi}")
	String DiaChi ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.SDT}")
	String SDT ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.Email}")
	@Email(message = "{Email.NguoiDung.Email}")
	String Email ;
	
	@NotNull(message = "{NotNull.NguoiDung.TrangThai}")
	boolean TrangThai;
	
	@NotBlank(message = "{NotBlank.NguoiDung.Hinh}")
	String Hinh ;
	
	@NotBlank(message = "{NotBlank.NguoiDung.VaiTro}")
	String VaiTro ;
	
	@OneToMany(mappedBy = "nguoidung")
    List<YeuThich> yeuthich;
	
	@OneToMany(mappedBy = "nguoidung")
    List<GioHang> giohang;
	
	@OneToMany(mappedBy = "nguoidung")
    List<DonHang> donhang;
}
