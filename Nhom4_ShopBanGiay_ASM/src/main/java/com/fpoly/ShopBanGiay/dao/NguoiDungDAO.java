package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.NguoiDung;
 

public interface NguoiDungDAO extends JpaRepository<NguoiDung, Integer>{
	@Query("SELECT o FROM NguoiDung o Where o.email Like  ?1 AND o.matkhau Like ?2")
	List<NguoiDung> findForAuthenticate(String Email, String pass);
	
	@Query("SELECT o FROM NguoiDung o Where o.email Like  ?1")
	NguoiDung findByEmail(String Email);
}
