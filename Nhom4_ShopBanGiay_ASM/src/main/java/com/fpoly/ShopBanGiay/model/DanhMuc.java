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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_dm")
	Integer madm;
	
	@NotBlank(message = "{NotBlank.DanhMuc.tendm}")
	@Size( max = 50 , message = "{Size.DanhMuc.tendm}")
	@Column(name = "ten_dm")
	private String tendm;
	
	@NotBlank(message = "{NotBlank.DanhMuc.anhdm}")
	@Column(name = "anh_dm")
	private String anhdm;
	
	@NotNull(message = "{NotNull.DanhMuc.trangthai}")
	@Column(name = "trang_thai")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "danhmuc")
    List<SanPham> sanpham;
}
