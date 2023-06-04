package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.Date;

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
	Integer MaYeuThich;
	Date NgayThich = new Date();
	Integer MaND;
	Integer MaSP;
	
	@ManyToOne
    @JoinColumn(name = "MaND")
    NguoiDung nguoidung;
	
	@ManyToOne
	@JoinColumn(name = "MaSP")
    SanPham sanpham;
}
