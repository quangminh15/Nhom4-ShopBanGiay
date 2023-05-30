package com.fpoly.ShopBanGiay.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
	@NotNull(message = "{NotNull.Size.MaSize}")
	private int MaSize;
	
	@NotNull(message = "{NotNull.Size.Size}")
	private Float Size;
}
