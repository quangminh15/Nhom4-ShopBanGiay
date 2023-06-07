package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="nhacungcap")
public class NhaCungCap  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_ncc")
	Integer mancc;
	@Column(name = "ten_ncc")
	String tenncc;
	@Column(name = "email")
	String email;
	@Column(name = "sdt")
	String sdt;
	@Column(name = "dia_chi")
	String diachi;
	
	@OneToMany(mappedBy = "nhacungcap")
    List<SanPham> sanpham;
}
