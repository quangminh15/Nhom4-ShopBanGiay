package com.fpoly.ShopBanGiay.model;


import java.io.Serializable;

import com.fpoly.ShopBanGiay.controller.donhangController;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="thanhtoan")
public class ThanhToan implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tt")
    Integer matt;
	
	@Column(name = "ngay_tao")
    java.util.Date ngaytao = new java.util.Date();
	
	@Column(name = "phuong_thuc")
	String phuongthuc;
	
	@Column(name = "trang_thai")
	String trangthai;
	
	@OneToOne
	@JoinColumn(name = "ma_dh",referencedColumnName = "ma_dh")
    DonHang donhang;
}
