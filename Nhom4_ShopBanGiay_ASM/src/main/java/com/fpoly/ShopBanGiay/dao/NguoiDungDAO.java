package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.NguoiDung;
 

public interface NguoiDungDAO extends JpaRepository<NguoiDung, Integer>{
	@Query("SELECT o FROM NguoiDung o Where o.email Like  ?1 AND o.matkhau Like ?2")
	List<NguoiDung> findForAuthenticate(String Email, String pass);
	
	// for Sign in
	@Query("SELECT o FROM NguoiDung o Where o.email Like  ?1")
	NguoiDung findByEmail(String Email);
	
	// for sign up & add user
	@Query("SELECT o FROM NguoiDung o Where o.email Like  ?1")
	List<NguoiDung> findByEmails(String Email);
	
	@Query("SELECT o FROM NguoiDung o Where o.sdt Like  ?1")
	List<NguoiDung> findByPhoneNumber(String phoneNumber);
	
	// when remove admin then check no under 1
	@Query("SELECT count(o) FROM NguoiDung o Where o.vaitro Like 1")
	int countAdmin();
	
	// 
	@Query("SELECT o  FROM NguoiDung o Where o.mand Like ?1")
	NguoiDung getUserByIdSure(int id);
	
	// for search
	Page<NguoiDung> findAllByHotenLike(String name, Pageable pageable);
}
