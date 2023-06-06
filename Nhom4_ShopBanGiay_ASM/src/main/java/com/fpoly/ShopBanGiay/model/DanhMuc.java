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
@Table(name = "DanhMuc")
public class DanhMuc  implements Serializable{
	@Id
	@NotNull(message = "{NotNull.DanhMuc.MaSize}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madm")
	private int MaDM;
	
	@NotBlank(message = "{NotBlank.DanhMuc.Size}")
	@Column(name = "tendm")
	private String TenDM;
	
	@NotBlank(message = "{NotBlank.DanhMuc.Size}")
	@Column(name = "anhdm")
	private String AnhDM;
	
	@NotNull(message = "{NotNull.DanhMuc.TrangThai}")
	@Column(name = "trangthai")
	private Boolean TrangThai;
	
	@OneToMany(mappedBy = "danhmuc")
    List<SanPham> sanpham;
}
