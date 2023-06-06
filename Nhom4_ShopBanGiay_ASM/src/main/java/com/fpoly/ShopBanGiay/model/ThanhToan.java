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
@Table(name="ThanhToan")
public class ThanhToan implements Serializable{
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
	
	@OneToOne
	@JoinColumn(name = "MaDH")
    DonHang donhang;
}
