package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Size")
public class Size  implements Serializable{
	@NotNull(message = "{NotNull.Size.MaSize}")
	private int MaSize;
	
	@NotNull(message = "{NotNull.Size.Size}")
	private Float Size;
	
	@NotNull(message = "{NotNull.Size.TrangThai}")
	private Boolean TrangThai;
	
	@OneToMany(mappedBy = "size")
    List<SanPhamSize> sanphamsize;
	
	
}
