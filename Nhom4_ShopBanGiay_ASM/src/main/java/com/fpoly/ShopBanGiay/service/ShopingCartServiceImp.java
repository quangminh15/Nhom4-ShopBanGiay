package com.fpoly.ShopBanGiay.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.fpoly.ShopBanGiay.dao.ChiTietDonHangDAO;
import com.fpoly.ShopBanGiay.dao.DonHangDAO;
import com.fpoly.ShopBanGiay.dao.GioHangDAO;
import com.fpoly.ShopBanGiay.dao.SanPhamSizeDAO;
import com.fpoly.ShopBanGiay.dao.ThanhToanDAO;
import com.fpoly.ShopBanGiay.model.ChiTietDonHang;
import com.fpoly.ShopBanGiay.model.DonHang;
import com.fpoly.ShopBanGiay.model.GioHang;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.SanPhamSize;
import com.fpoly.ShopBanGiay.model.ThanhToan;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShopingCartServiceImp implements ShoppingCartService {

	@Autowired
	private GioHangDAO ghDAO;
	
	@Autowired
	private ThanhToanDAO ttDAO;
	
	@Autowired
	private SanPhamSizeDAO spsDAO;
	
	@Autowired
	private DonHangDAO dhDAO;
	
	@Autowired
	private ChiTietDonHangDAO ctDAO;
	
	public List<GioHang> listGioHang(NguoiDung nguoidung) {
		return ghDAO.findByNguoidung(nguoidung);
	}
	
	@Override
	public Integer addToCart(Integer masps, Integer soluong, NguoiDung nguoiDung) {
		
		Integer addQty = soluong;
		
		SanPhamSize sps = spsDAO.findById(masps).get();
		
		GioHang cart = ghDAO.findByNguoidungAndSanphamsize(nguoiDung, sps);
		
		if (cart != null) {
			addQty = cart.getSoluong()+soluong;
			cart.setSoluong(addQty);
		}else {
		cart = new GioHang();
		cart.setNguoidung(nguoiDung);
		cart.setSanphamsize(sps);
		cart.setSoluong(soluong);
		}
		
		ghDAO.save(cart);
		
		
		return addQty;
	}

	@Override
	public Double updateQuty(Integer masps, Integer soluong, NguoiDung nguoidung) {
		ghDAO.updateQty(soluong, masps, nguoidung.getMand());
		System.out.print(masps+","+soluong+","+nguoidung.getMand());
		SanPhamSize sps = spsDAO.findById(masps).get();
		double subtotal = sps.getSanpham().getGiamgiasp()*soluong;
		return subtotal;
	}

	@Override
	public void addPayment(String method) {
		
		ThanhToan payment = new ThanhToan();
		
	}

	@Override
	public
	 DonHang addOrder(NguoiDung nguoidung, String diachi, String nguoinhan, String sdt,Double tongtien) {
		
		DonHang order = new DonHang();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	    java.util.Date date = new java.util.Date(); 
	    
		order.setNgaytao(formatter.format(date));
		order.setNguoidung(nguoidung);
		order.setNguoinhan(nguoinhan);
		order.setDiachigiaohang(diachi);
		order.setSdtnhanhang(sdt);
		order.setTongtien(tongtien);
		order.setTrangthai("Đang Chờ Xác Nhận");
		
		dhDAO.save(order);
		
		return order;
	}
	
	@Override
	public
	 ThanhToan addPayment(DonHang donhang) {
		
		ThanhToan pay = new ThanhToan();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	    java.util.Date date = new java.util.Date(); 
	    
	    pay.setNgaytao(formatter.format(date));
	    pay.setDonhang(donhang);
	    pay.setPhuongthuc("Thanh toán bằng tiền mặt");
	    pay.setTrangthai("Đã thanh toán");
		
		
		ttDAO.save(pay);
		
		return pay;
	}

	@Override
	public void addCartItemsToOderDetails(NguoiDung nguoidung, DonHang donhang) {
		
//		 DonHang dh = dhDAO.findByNguoidungAndTrangthai(nguoidung, "Đang Chờ Xác Nhận"); 
		 
		
		 List<GioHang> gh = ghDAO.findByNguoidung(nguoidung);
		 for (GioHang gioHang : gh) {
			 ChiTietDonHang ct = new ChiTietDonHang();
			 
			 SanPhamSize sps = spsDAO.findById(gioHang.getSanphamsize().getMasps()).get();
			 
			 ct.setDonhang(donhang);
			 ct.setSanphamsize(gioHang.getSanphamsize());
			 ct.setSoluong(gioHang.getSoluong());
			 
			 ctDAO.save(ct);
			 
			 sps.setSoluong(sps.getSoluong()-ct.getSoluong());
			 
			 spsDAO.save(sps);
			}
		
	}

	@Override
	public void remove(Integer id) {
		ghDAO.deleteById(id);
		
	}

	@Override
	public void removeAll(Integer mand) {
		
		ghDAO.clear(mand);;
		
	}
}
