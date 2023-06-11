package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.YeuThich;

public interface YeuThichDAO extends JpaRepository<YeuThich, Integer> {
	@Query ("select yt from YeuThich yt where yt.nguoidung.mand like ?1")
	List<YeuThich> findYeuThichByID(Integer mand);
}
