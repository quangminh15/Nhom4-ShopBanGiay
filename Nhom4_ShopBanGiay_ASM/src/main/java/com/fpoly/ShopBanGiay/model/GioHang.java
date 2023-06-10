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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name="giohang", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"ma_nd","ma_sps"})
})
public class GioHang implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_gh")
    Integer magh;
	
	@Column(name = "so_luong")
    Integer soluong;
	
	
	@ManyToOne
    @JoinColumn(name = "ma_sps")
    SanPhamSize sanphamsize;
	
	@ManyToOne
    @JoinColumn(name = "ma_nd")
    NguoiDung nguoidung;
}
