package com.fpoly.ShopBanGiay.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="ThanhToan")
public class ThanhToan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    Integer maTT;
	
	@Column(name = "NgayTao")
    java.util.Date ngayTao = new java.util.Date();
	
	@Column(name = "PhuongThuc")
	String phuongThuc;
	
	@Column(name = "TrangThai")
	String trangThai;
}
