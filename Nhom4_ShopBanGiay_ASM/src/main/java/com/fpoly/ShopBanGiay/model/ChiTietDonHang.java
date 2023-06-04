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
@Table(name="ChiTietDonHang")
public class ChiTietDonHang {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaCTDH")
    Integer maCTDH;

	@Column(name = "SoLuong")
	Integer soLuong;
}
