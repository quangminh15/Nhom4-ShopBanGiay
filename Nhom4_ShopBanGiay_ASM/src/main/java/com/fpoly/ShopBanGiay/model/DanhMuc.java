package com.fpoly.ShopBanGiay.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhMuc {
	@NotNull(message = "{NotNull.DanhMuc.MaSize}")
	private int MaDM;
	
	@NotBlank(message = "{NotBlank.DanhMuc.Size}")
	private String TenDM;
	
	@NotBlank(message = "{NotBlank.DanhMuc.Size}")
	private String AnhDM;
	
	@NotNull(message = "{NotNull.DanhMuc.TrangThai}")
	private Boolean TrangThai;
}
