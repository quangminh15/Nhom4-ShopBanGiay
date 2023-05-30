package com.fpoly.ShopBanGiay.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
	@NotNull(message = "{NotNull.SanPham.MaSP}")
	private int MaSP;
	
	@NotBlank(message = "{NotBlank.SanPham.TenSP}")
	private String TenSP;
	
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh1}")
	private String HinhAnh1;
	
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh2}")
	private String HinhAnh2;
	
	@NotBlank(message = "{NotBlank.SanPham.HinhAnh3}")
	private String HinhAnh3;
	
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
}