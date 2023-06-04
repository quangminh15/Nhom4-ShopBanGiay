package com.fpoly.ShopBanGiay.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="NhaCungCap")
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer MaNCC;
	String TenNCC;
	String Email;
	String SDT;
	String DiaChi;
}
