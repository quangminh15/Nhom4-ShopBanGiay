package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.NhaCungCap;

public interface NhaCungCapDAO extends JpaRepository<NhaCungCap, Integer> {
	@Query("SELECT o FROM NhaCungCap o Where o.sdt Like  ?1")
	List<NhaCungCap> findByPhone(String phone);
	
	@Query("SELECT o FROM NhaCungCap o Where o.email Like  ?1")
	List<NhaCungCap> findByEmail(String email);
	
	@Query("SELECT o FROM NhaCungCap o Where o.tenncc Like  ?1")
	List<NhaCungCap> findByTenncc(String tenncc);
}
