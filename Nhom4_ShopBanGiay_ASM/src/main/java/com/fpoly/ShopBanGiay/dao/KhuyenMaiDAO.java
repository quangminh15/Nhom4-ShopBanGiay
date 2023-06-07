package com.fpoly.ShopBanGiay.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.ShopBanGiay.model.GiamGia;

public interface KhuyenMaiDAO extends JpaRepository<GiamGia, Integer> {

}
