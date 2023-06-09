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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sanphamsize", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"ma_size","ma_sp"})
})
public class SanPhamSize  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "{NotNull.SanPhamSize.MaSPS}")
	@Column(name = "ma_sps")
	private int masps;
	
	@Column(name = "so_luong")
	@NotNull(message = "{NotNull.SanPhamSize.SoLuong}")
	private int soluong;
	
	@ManyToOne
    @JoinColumn(name = "ma_size")
    Size size;
	
	@ManyToOne
    @JoinColumn(name = "ma_sp")
    SanPham sanpham;
	
	@ManyToOne
    @JoinColumn(name = "ma_sps",insertable=false, updatable=false)
    ChiTietDonHang chitietdonhang;
	
	@OneToMany(mappedBy = "sanphamsize")
	List<ChiTietGioHang> chitietgiohang;
	

}
