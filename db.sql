go
use master
go
create database nhom4_shopBangiay
go
use nhom4_shopBangiay


--1 người dùng
go 
create table nguoidung(
	ma_nd bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	mat_khau nvarchar(50) NOT NULL,
	ho_ten nvarchar(50) NOT NULL,
	dia_chi nvarchar(250) NOT NULL,
	sdt varchar(11) NOT NULL,
	email nvarchar(50) NOT NULL,
	trang_thai bit NOT NULL DEFAULT 0,
	hinh nvarchar(50) NOT NULL,
	vai_tro bit NOT NULL default 0
)
--2 danh mục 
go
create table danhmuc(
	ma_dm bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ten_dm nvarchar(50) NOT NULL,
	anh_dm varchar(50) NOT NULL,
	trang_thai bit NOT NULL default 0
)
--3 nhà cung cấp
go 
create table nhacungcap(
	ma_ncc bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ten_ncc nvarchar(50) NOT NULL,
	email nvarchar(50) NOT NULL,
	sdt varchar(10) NOT NULL,
	dia_chi nvarchar(250) NOT NULL,
)
--4 giảm giá
go 
create table giamgia(
	ma_giam_gia bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ten_giam_gia nvarchar(50) NOT NULL,
	giam_gia Float Default 0,
	ngay_tao Date NOT NULL,
	ngay_ket_thuc Date NOT NULL,
	mo_ta nvarchar(250) NULL
)
--5 size
go 
create table size(
	ma_size bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	size_giay int NOT NULL,
	trang_thai bit NOT NULL default 0
)
--6 sản phẩm
go 
create table sanpham(
	ma_sp bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ten_sp nvarchar(50) NOT NULL,
	hinh_anh1 nvarchar(50) NOT NULL,
	hinh_anh2 nvarchar(50) NOT NULL,
	hinh_anh3 nvarchar(50) NOT NULL,
	loai bit not null default 0,
	gia Float  Default 0,
	mo_ta nvarchar(250) NOT NULL,
	trang_thai bit NOT NULL default 0,
	ma_dm bigint NOT NULL,
	ma_ncc bigint NOT NULL,
	ma_giam_gia bigint NULL
)


--7 yêu thích
go 
create table yeuthich(
	ma_yeu_thich bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ngay_thich Date NOT NULL,
	ma_nd bigint NOT NULL,
	ma_sp bigint NOT NULL
)
--8 sản phẩm size
go 
create table sanphamsize(
	ma_sps bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ma_sp bigint NOT NULL,
	ma_size bigint NOT NULL,
	so_luong bigint not null
)
--9 giỏ hàng
go 
create table giohang(
	ma_gh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ma_nd bigint NOT NULL
)
--10 Chi tiết Giỏ Hàng
go
create table chitietgiohang(
	ma_ctgh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ma_gh bigint  NOT NULL,
	ma_sps bigint  NOT NULL,
	so_luong int  NOT NULL,
	
	
	
	CONSTRAINT FK_Cart FOREIGN KEY (ma_gh)
    REFERENCES giohang(ma_gh),

	CONSTRAINT FK_Productsize FOREIGN KEY (ma_sps)
    REFERENCES sanphamsize(ma_sps)

)
--11 đơn hàng
go 
create table donhang(
	ma_dh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ma_nd bigint NOT NULL,
	ngay_tao Date NOT NULL,
	tong_tien Float Default 0,
	dia_chigiaohang nvarchar(250) NOT NULL,
	nguoi_nhan nvarchar(150) NOT NULL, 
	sdt_nhanhang varchar(11) NOT NULL,
	trang_thai nvarchar(50) NOT NULL
)

--12 chi tiết đơn hàng

go 
create table chitietdonhang(
	ma_ctdh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ma_dh bigint NOT NULL,
	ma_spsize bigint NOT NULL,
	so_luong int NOT NULL
)
--13 thanh toán
go
create table thanhtoan(
	ma_tt bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ma_dh bigint NOT NULL,
	ngay_tao date NOT NULL,
	phuong_thuc nvarchar(50) NOT NULL,
	trang_thai bit NOT NULL Default 0
)
-- Tạo các khóa duy nhất
go
ALTER TABLE  thanhtoan ADD CONSTRAINT unique1 UNIQUE (ma_dh);
go
ALTER TABLE  sanphamsize ADD CONSTRAINT unique2 UNIQUE (ma_sp, ma_size);
go
ALTER TABLE chitietgiohang
  ADD CONSTRAINT uqchitietgiohang UNIQUE(ma_gh, ma_sps);
go
--Tạo liên kết các bảng
ALTER TABLE sanpham
ADD CONSTRAINT FK_DMtoSP
FOREIGN KEY (ma_dm) REFERENCES danhmuc(ma_dm);

go
ALTER TABLE sanpham
ADD CONSTRAINT FK_NCCtoSP
FOREIGN KEY (ma_ncc) REFERENCES nhacungcap(ma_ncc);

go
ALTER TABLE sanpham
ADD CONSTRAINT FK_GGtoSP
FOREIGN KEY (ma_giam_gia) REFERENCES giamgia(ma_giam_gia);

go
ALTER TABLE yeuthich
ADD CONSTRAINT FK_SPtoYT
FOREIGN KEY (ma_sp) REFERENCES sanpham(ma_sp);

go
ALTER TABLE yeuthich
ADD CONSTRAINT FK_NDtoYT
FOREIGN KEY (ma_nd) REFERENCES nguoidung(ma_nd);

go
ALTER TABLE thanhtoan
ADD CONSTRAINT FK_DHtoTT
FOREIGN KEY (ma_dh) REFERENCES donhang(ma_dh);

go
ALTER TABLE donhang
ADD CONSTRAINT FK_NDtoDH
FOREIGN KEY (ma_nd) REFERENCES nguoidung(ma_nd);

/*go
ALTER TABLE giohang
ADD CONSTRAINT FK_SPStoGH
FOREIGN KEY (ma_sps) REFERENCES sanphamsize(ma_sps);
*/
go
ALTER TABLE giohang
ADD CONSTRAINT FK_NDtoGH
FOREIGN KEY (ma_nd) REFERENCES nguoidung(ma_nd);

go
ALTER TABLE sanphamsize
ADD CONSTRAINT FK_SPtoSPS
FOREIGN KEY (ma_sp) REFERENCES sanpham(ma_sp);

go
ALTER TABLE sanphamsize
ADD CONSTRAINT FK_sizetoSPS
FOREIGN KEY (ma_size) REFERENCES size(ma_size);

go
ALTER TABLE chitietdonhang
ADD CONSTRAINT FK_DHtoCTDH
FOREIGN KEY (ma_dh) REFERENCES donhang(ma_dh);

go
ALTER TABLE chitietdonhang
ADD CONSTRAINT FK_SPStoCTDH
FOREIGN KEY (ma_spsize) REFERENCES sanphamsize(ma_sps);

--Thêm dữ liệu
--1 người dùng
go
INSERT INTO nguoidung (mat_khau, ho_ten, dia_chi, sdt, email, trang_thai,hinh, vai_tro)
VALUES ('Btb.123', 'Bùi Thanh Bùi', '123 Đường ABC, Phường XYZ, Quận NNN Cần Thơ, Việt Nam', '0847151739', 
'btb@gmail.com',0, 'btb.png', 0);
INSERT INTO nguoidung (mat_khau, ho_ten, dia_chi, sdt, email, trang_thai,hinh, vai_tro)
VALUES ('nva.123', 'Nguyễn Văn An', '123 Đường ABC, Phường XYZ, Quận NNN Cần Thơ, Việt Nam', '0847151123', 
'nva@gmail.com',0, 'nva.png', 1);
INSERT INTO nguoidung (mat_khau, ho_ten, dia_chi, sdt, email, trang_thai,hinh, vai_tro)
VALUES
('tvb.123', 'Tran Van Binh', '123 Đường ABC, Phường XYZ, Quận NNN Cần Thơ, Việt Nam', '0844576123', 
'nva@gmail.com',0, 'nva.png', 1);
--2 danh mục 
go
INSERT INTO danhmuc (ten_dm,anh_dm,trang_thai) 
VALUES (N'Converse','category1.jpg',0),
	   (N'Vans','category2.jpg',0),
	   (N'Adidas','category3.jpg',0),
	   (N'Nike','category4.jpg',0),
	   (N'Superme','category5.jpg',1);

--3 nhà cung cấp
go
INSERT INTO nhacungcap Values ('Bitis', 'bitis123@gmail.com', '0946273998', N'22 Lý Chiêu Hoàng, Phường 10, Quận 6, TP. HCM');
INSERT INTO nhacungcap Values ('Juno', 'cskh@juno.vn', '0956483958', N'313 Nguyễn Thị Thập, Phường Tân Phú, Quận 7, TP. HCM');
INSERT INTO nhacungcap Values ('Vinagiay', 'cskh@vinagiay.vn', '0957684938', N'180 - 182 Lý Chính Thắng, Quận 3, TP. HCM');
INSERT INTO nhacungcap Values ('EvaShoes', 'Info@evashoes.com.vn', '0946583875', N'Tầng 1, Số 26 Nguyễn Phong Sắc, Dịch Vọng, Cầu Giấy, Hà Nội');
--4 giảm giá
go
INSERT INTO giamgia Values (N'Tháng Yêu Thương - Bao La Khuyến Mãi', 70, '2023-06-08', '2023-09-01', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
INSERT INTO giamgia Values (N'Bóc Thăm Trúng Thưởng', 50, '2023-06-01', '2023-07-01', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
INSERT INTO giamgia Values (N'Ngày Vàng Thứ Hai', 50, '2023-06-10', '2023-06-20', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
INSERT INTO giamgia Values (N'Sale Độc Nhât ', 40, '2023-06-10', '2023-08-01', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
--5 size
go
INSERT INTO size (size_giay,trang_thai) 
VALUES (35,0),
	   (36,0),
	   (37,0),
	   (38,0),
	   (39,1);

--6 sản phẩm
go
INSERT INTO sanpham (ten_sp,hinh_anh1,hinh_anh2,hinh_anh3,loai,gia,mo_ta,trang_thai,ma_dm,ma_ncc,ma_giam_gia) 
VALUES (N'Converse Run Star Hike','anh10-1.png','anh10-2.png','anh10-3.png',0,200,N'Đôi giày Run Star Hike với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn.',0,1,1,1),
	   (N'Converse Renew Canvas','hinh7-1.jpg','hinh7-2.jpg','hinh7-3.jpg',1,300,N'Converse Renew Canvas, phiên bản giới hạn mang mục đích bảo vệ môi trường sẽ được chính thức mở bán tại hệ thống Converse VN từ ngày 5/7 với số lượng giới hạn.',0,1,1,1),
	   (N'Converse Run Star Move','anh9-1.jpg','anh9-2.jpg','anh9-3.jpg',0,500,N'1Đôi giày Run Star Move với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố.',0,1,1,1),
	   (N'Vans Authen DX BW','hinh6-1.jpg','hinh6-2.jpg','hinh6-3.jpg',1,450,N'Đôi giày Run Star Move với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố.',0,2,1,1),
	   (N'Vans SK8-Hi BW','anh11-1.jpg','anh11-2.jpg','anh11-3.jpg',0,900,N'Vans SK8-Hi với thiết kế cổ cao qua mắt cá chân và giữ lại chi tiết lượn sóng đặc trưng 2 bên thân giày. Sử dụng kết hợp cả 2 chất liệu Canvas và da lộn mềm mại giúp form giày ôm chân hơn. ',0,2,1,1);

--7 yêu thích
go
INSERT INTO yeuthich Values ('2023-08-08', 1, 1);
INSERT INTO yeuthich Values ('2023-06-28', 2, 5);
INSERT INTO yeuthich Values ('2023-06-28', 2, 3);
INSERT INTO yeuthich Values ('2023-07-17', 2, 4);
INSERT INTO yeuthich Values ('2023-07-18', 2, 2);
INSERT INTO yeuthich Values ('2023-08-08', 2, 1);

--8 sản phẩm size
go
INSERT INTO sanphamsize (ma_sp,ma_size,so_luong) 
VALUES (1,1,5),
	   (2,1,15),
	   (3,1,25),
	   (4,2,35),
	   (5,5,45);

--9 giỏ hàng
go
INSERT INTO giohang(ma_nd) 
VALUES (3),
	   (3),
	   (3),
	   (2),
	   (2);
	   
--10 Giỏ hàng chi tiết
go
INSERT INTO chitietgiohang(ma_gh,ma_sps,so_luong) 
VALUES (1,3,3),
		(2,3,3);
--11 đơn hàng
go
INSERT INTO donhang(ma_nd,ngay_tao,tong_tien,dia_chigiaohang,nguoi_nhan,sdt_nhanhang,trang_thai)
VALUES (2,'2023-06-28',0,N'123 Đường Số 1, Phường An Bình, Quận Ninh Kiều Cần Thơ,',N'Nguyễn Trần Minh Nhân','0943857632',N'Đang Chờ Xác Nhận');
--12 chi tiết đơn hàng
--13 thanh toán

