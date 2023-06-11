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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="nhacungcap")
public class NhaCungCap  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_ncc")
	private Integer mancc;
	
	@NotBlank(message = "{NotNull.NhaCungCap.tenncc}")
	@Column(name = "ten_ncc")
	private String tenncc;
	
	@NotBlank(message = "{NotNull.NhaCungCap.email}")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "{NotNull.NhaCungCap.sdt}")
	@Column(name = "sdt")
	private String sdt;
	
	@NotBlank(message = "{NotNull.NhaCungCap.diachi}")
	@Column(name = "dia_chi")
	private String diachi;
	
	@OneToMany(mappedBy = "nhacungcap")
    List<SanPham> sanpham;
}
