package com.fpoly.ShopBanGiay.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamSize {
	
	@NotNull(message = "{NotNull.SanPhamSize.MaSPS}")
	private int MaSPS;
	
	@NotNull(message = "{NotNull.SanPhamSize.MaSP}")
	private int MaSP;
	
	@NotNull(message = "{NotNull.SanPhamSize.MaSize}")
	private int MaSize;
	
	@NotNull(message = "{NotNull.SanPhamSize.SoLuong}")
	private int SoLuong;
	
}
