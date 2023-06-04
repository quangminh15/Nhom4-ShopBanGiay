package com.fpoly.ShopBanGiay.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="GiamGia")
public class GiamGia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer MaGiamGia;
	String TenGiamGia;
	Float GiamGia;
	Date NgayTao = new Date();
	Date NgayKetThuc = new Date();
	String MoTa;
}
