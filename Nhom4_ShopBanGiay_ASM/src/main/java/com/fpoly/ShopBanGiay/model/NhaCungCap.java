package com.fpoly.ShopBanGiay.model;

import java.io.Serializable;
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
@Table(name="NhaCungCap")
public class NhaCungCap  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer mancc;
	String tenncc;
	String email;
	String sdt;
	String diachi;
	
	@OneToMany(mappedBy = "nhacungcap")
    List<SanPham> sanpham;
}
