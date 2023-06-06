package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="GiamGia")
public class GiamGia  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer magiamgia;
	String tengiamgia;
	Float giamgia;
	Date ngaytao = new Date();
	Date ngayketthuc = new Date();
	String mota;
	
	@OneToMany(mappedBy = "giamgia")
    List<SanPham> sanpham;
}
