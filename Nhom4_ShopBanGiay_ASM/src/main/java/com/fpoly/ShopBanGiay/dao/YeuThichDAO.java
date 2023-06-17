package com.fpoly.ShopBanGiay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.ReportYT;
import com.fpoly.ShopBanGiay.model.SanPham;
import com.fpoly.ShopBanGiay.model.YeuThich;

public interface YeuThichDAO extends JpaRepository<YeuThich, Integer> {
	public List<YeuThich> findByNguoidung(NguoiDung nguoidung);
	
	public YeuThich findByNguoidungAndSanpham(NguoiDung nguoidung,SanPham sanpham);
	
	@Query ("select yt from YeuThich yt where yt.nguoidung.mand like ?1")
	List<YeuThich> findYeuThichByID(Integer mand);
	
	@Query ("select yt from YeuThich yt where yt.sanpham.masp like ?1")
	List<YeuThich> findYeuThichSP(Integer masp);
	
	@Query ("select yt from YeuThich yt where yt.nguoidung.mand like ?1")
	List<YeuThich> findYeuThichND(Integer mand);
	
	@Query("SELECT new ReportYT(o.sanpham, count(o.nguoidung)) "
			+ " FROM YeuThich o "
			+ " GROUP BY o.sanpham"
			+ " ORDER BY count(o.nguoidung) DESC"
			)
	List<ReportYT> getReportYT();
}
