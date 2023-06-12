package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="yeuthich")
public class YeuThich  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_yeu_thich")
	Integer mayeuthich;
	@Column(name = "ngay_thich")
	Date ngaythich = new Date();
	
//	@Column(name = "ma_nd")
//	Integer mand;
//	
//	@Column(name = "ma_sp")
//	Integer masp;
	
	@ManyToOne
    @JoinColumn(name = "ma_nd")
    NguoiDung nguoidung;
	
	@ManyToOne
	@JoinColumn(name = "ma_sp")
    SanPham sanpham;
}
