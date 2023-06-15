package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "sanphamsize", uniqueConstraints = { @UniqueConstraint(columnNames = { "ma_size", "ma_sp" }) })
public class SanPhamSize implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_sps")
	Integer masps;

	@Column(name = "so_luong")
	@NotNull(message = "{NotNull.SanPhamSize.soluong}")
	Integer soluong;

	@ManyToOne
	@JoinColumn(name = "ma_size")
	Size size;
	
	
	@ManyToOne
	@JoinColumn(name = "ma_sp")
	SanPham sanpham;

	
	@OneToMany(mappedBy = "sanphamsize")
	List<ChiTietDonHang> chitietdonhang;
	
	@OneToMany(mappedBy = "sanphamsize")
	List<GioHang> giohang;

}
