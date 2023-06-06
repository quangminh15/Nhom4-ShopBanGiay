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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Size")
public class Size  implements Serializable{
	@Id
	@NotNull(message = "{NotNull.Size.MaSize}")
	@Column(name = "masize")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MaSize;
	
	@NotNull(message = "{NotNull.Size.Size}")
	@Column(name = "sizegiay")
	private Float Size;
	
	@NotNull(message = "{NotNull.Size.TrangThai}")
	@Column(name = "trangthai")
	private Boolean TrangThai;
	
	@OneToMany(mappedBy = "size")
    List<SanPhamSize> sanphamsize;
	
	
}
