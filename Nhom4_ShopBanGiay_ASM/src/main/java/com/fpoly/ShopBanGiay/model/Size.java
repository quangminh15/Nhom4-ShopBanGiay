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
@Table(name = "size")
public class Size  implements Serializable{
	@Id
	@NotNull(message = "{NotNull.Size.masize}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_size")
	private Integer masize;
	
	@NotNull(message = "{NotNull.Size.sizegiay}")
	@Column(name = "size_giay")
	private Float sizegiay;
	
	@NotNull(message = "{NotNull.Size.trangthai}")
	@Column(name = "trang_thai")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "size")
    List<SanPhamSize> sanphamsize;
	
	public String getTrangThaiGiay() {
		if (trangthai = true) {
			return "Hoạt động";
		} else
			return "Không hoạt động";
	}
	
}
