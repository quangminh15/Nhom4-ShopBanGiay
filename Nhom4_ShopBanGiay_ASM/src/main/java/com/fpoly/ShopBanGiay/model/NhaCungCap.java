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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Email(message = "Email không hợp lệ")
	@Column(name = "email")
	private String email;
	
//	@NotBlank(message = "{NotNull.NhaCungCap.sdt}")
//	@Size(min = 10, max=10, message ="SDT không hợp lệ")
	@Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "SDT không hợp lệ")
	@Column(name = "sdt")
	private String sdt;
	
	@NotBlank(message = "{NotNull.NhaCungCap.diachi}")
	@Column(name = "dia_chi")
	private String diachi;
	
	@OneToMany(mappedBy = "nhacungcap")
    List<SanPham> sanpham;
}
