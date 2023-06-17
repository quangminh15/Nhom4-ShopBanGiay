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
import jakarta.validation.constraints.Min;
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
	
	@Size(min = 4, max = 50, message = "Tên nhà cung cấp từ 4 ---> 50 kí tự!!!")
	@Column(name = "ten_ncc")
	private String tenncc;
	
	@NotBlank(message = "{NotNull.NhaCungCap.email}")
	@Email(message = "Email không hợp lệ")
	@Column(name = "email")
	private String email;
	
//	@NotBlank(message = "{NotNull.NhaCungCap.sdt}")
	@Pattern(regexp = "^(0[1-9][0-9]{8,9})$", message = "SDT không hợp lệ")
	@Column(name = "sdt")
	private String sdt;
	
//	@NotBlank(message = "{NotNull.NhaCungCap.diachi}")
	@Size(min = 10, max = 250, message = "Địa chỉ từ 10 -----> 250 kí tự!!!!")
	@Column(name = "dia_chi")
	private String diachi;
	
	@OneToMany(mappedBy = "nhacungcap")
    List<SanPham> sanpham;
}
