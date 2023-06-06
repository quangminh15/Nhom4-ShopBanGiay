package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
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
@Table(name="YeuThich")
public class YeuThich  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_yeu_thich")
	Integer mayeuthich;
	@Column(name = "ngay_thich")
	Date ngaythich = new Date();
//	Integer mand;
//	Integer masp;
	
	@ManyToOne
    @JoinColumn(name = "ma_nd")
    NguoiDung nguoidung;
	
	@ManyToOne
	@JoinColumn(name = "ma_sp")
    SanPham sanpham;
}
