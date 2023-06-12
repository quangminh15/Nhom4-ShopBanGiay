package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="giamgia")
public class GiamGia  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_giam_gia")
	private Integer magiamgia;
	
	@NotBlank(message = "{NotNull.GiamGia.tengiamgia}")
	@Column(name = "ten_giam_gia")
	private String tengiamgia;
	
	@NotNull(message = "{NotNull.GiamGia.giamgia}")
	@Min(value = 0, message = "Khuyến mãi từ 0 đến 100 (%)")
	@Max(value = 100, message = "Khuyến mãi từ 0 đến 100 (%)")
	@Column(name = "giam_gia")
	private Float giamgia;
	
	@NotBlank(message = "{NotNull.GiamGia.ngaytao}")
	@Column(name = "ngay_tao")
	private String ngaytao;
	
	@NotBlank(message = "{NotNull.GiamGia.ngayketthuc}")
	@Column(name = "ngay_ket_thuc")
	private String ngayketthuc;
	
	@Column(name = "mo_ta")
	private String mota;
	
	@OneToMany(mappedBy = "giamgia")
    List<SanPham> sanpham;
}
