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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "danhmuc")
public class DanhMuc  implements Serializable{
	@Id
	@NotNull(message = "{NotNull.DanhMuc.MaSize}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_dm")
	private int madm;
	
	@NotBlank(message = "{NotBlank.DanhMuc.Size}")
	@Column(name = "ten_dm")
	private String tendm;
	
	@NotBlank(message = "{NotBlank.DanhMuc.Size}")
	@Column(name = "anh_dm")
	private String anhdm;
	
	@NotNull(message = "{NotNull.DanhMuc.TrangThai}")
	@Column(name = "trang_thai")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "danhmuc")
    List<SanPham> sanpham;
}
