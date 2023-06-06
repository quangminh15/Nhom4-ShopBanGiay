go
use master
go
create database nhom4_shopBangiay
go
use nhom4_shopBangiay


--1 người dùng
go 
create table NguoiDung(
	mand bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	matkhau nvarchar(50) NOT NULL,
	hoten nvarchar(50) NOT NULL,
	diachi nvarchar(250) NOT NULL,
	sdt varchar(11) NOT NULL,
	email nvarchar(50) NOT NULL,
	trangthai bit NOT NULL DEFAULT 0,
	hinh nvarchar(50) NOT NULL,
	vaitro bit NOT NULL default 0
)
--2 danh mục 
go
create table DanhMuc(
	madm bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	tendm nvarchar(50) NOT NULL,
	anhdm varchar(50) NOT NULL,
	trangthai bit NOT NULL default 0
)
--3 nhà cung cấp
go 
create table NhaCungCap(
	mancc bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	tenncc nvarchar(50) NOT NULL,
	email nvarchar(50) NOT NULL,
	sdt varchar(10) NOT NULL,
	diachi nvarchar(250) NOT NULL,
)
--4 giảm giá
go 
create table giamgia(
	magiamgia bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	tengiamgia nvarchar(50) NOT NULL,
	giamgia Float Default 0,
	ngaytao Date NOT NULL,
	ngayketthuc Date NOT NULL,
	mota nvarchar(250) NULL
)
--5 size
go 
create table Size(
	masize bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	sizegiay int NOT NULL,
	trangthai bit NOT NULL default 0
)
--6 sản phẩm
go 
create table SanPham(
	masp bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	tensp nvarchar(50) NOT NULL,
	hinhanh1 nvarchar(50) NOT NULL,
	hinhanh2 nvarchar(50) NOT NULL,
	hinhanh3 nvarchar(50) NOT NULL,
	loai bit not null default 0,
	gia Float  Default 0,
	mota nvarchar(250) NOT NULL,
	trangthai bit NOT NULL default 0,
	madm bigint NOT NULL,
	mancc bigint NOT NULL,
	magiamgia bigint NULL
)


--7 yêu thích
go 
create table YeuThich(
	mayeuthich bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ngaythich Date NOT NULL,
	mand bigint NOT NULL,
	masp bigint NOT NULL
)
--8 sản phẩm size
go 
create table SanPhamSize(
	masps bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	masp bigint NOT NULL,
	masize bigint NOT NULL,
	soluong bigint not null
)
--9 giỏ hàng
go 
create table GioHang(
	magh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	mand bigint NOT NULL
)
--10 Chi tiết Giỏ Hàng
go
create table ChiTietGioHang(
	magh bigint  NOT NULL,
	masps bigint  NOT NULL,
	soluong int  NOT NULL,
	
	CONSTRAINT PK_CartDetail PRIMARY KEY (magh,masps),
	
	CONSTRAINT FK_Cart FOREIGN KEY (magh)
    REFERENCES GioHang(magh),

	CONSTRAINT FK_ProductSize FOREIGN KEY (masps)
    REFERENCES SanPhamSize(masps)

)
--11 đơn hàng
go 
create table DonHang(
	madh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	mand bigint NOT NULL,
	ngaytao Date NOT NULL,
	tongtien Float Default 0,
	diachigiaohang nvarchar(250) NOT NULL,
	nguoinhan nvarchar(150) NOT NULL, 
	sdtnhanhang varchar(11) NOT NULL,
	trangthai nvarchar(50) NOT NULL
)

--12 chi tiết đơn hàng

go 
create table ChiTietDonHang(
	mactdh bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	madh bigint NOT NULL,
	masps bigint NOT NULL,
	soluong int NOT NULL
)
--13 thanh toán
go
create table ThanhToan(
	matt bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	madh bigint NOT NULL,
	ngaytao date NOT NULL,
	phuongthuc nvarchar(50) NOT NULL,
	trangthai bit NOT NULL Default 0
)
-- Tạo các khóa duy nhất
go
ALTER TABLE  ThanhToan ADD CONSTRAINT unique1 UNIQUE (madh);
go
ALTER TABLE  SanPhamSize ADD CONSTRAINT unique2 UNIQUE (masp, masize);
go
--Tạo liên kết các bảng
ALTER TABLE SanPham
ADD CONSTRAINT FK_DMtoSP
FOREIGN KEY (madm) REFERENCES DanhMuc(madm);

go
ALTER TABLE SanPham
ADD CONSTRAINT FK_NCCtoSP
FOREIGN KEY (mancc) REFERENCES NhaCungCap(mancc);

go
ALTER TABLE SanPham
ADD CONSTRAINT FK_GGtoSP
FOREIGN KEY (magiamgia) REFERENCES giamgia(magiamgia);

go
ALTER TABLE YeuThich
ADD CONSTRAINT FK_SPtoYT
FOREIGN KEY (masp) REFERENCES SanPham(masp);

go
ALTER TABLE YeuThich
ADD CONSTRAINT FK_NDtoYT
FOREIGN KEY (mand) REFERENCES NguoiDung(mand);

go
ALTER TABLE ThanhToan
ADD CONSTRAINT FK_DHtoTT
FOREIGN KEY (madh) REFERENCES DonHang(madh);

go
ALTER TABLE DonHang
ADD CONSTRAINT FK_NDtoDH
FOREIGN KEY (mand) REFERENCES NguoiDung(mand);

/*go
ALTER TABLE GioHang
ADD CONSTRAINT FK_SPStoGH
FOREIGN KEY (masps) REFERENCES SanPhamSize(masps);
*/
go
ALTER TABLE GioHang
ADD CONSTRAINT FK_NDtoGH
FOREIGN KEY (mand) REFERENCES NguoiDung(mand);

go
ALTER TABLE SanPhamSize
ADD CONSTRAINT FK_SPtoSPS
FOREIGN KEY (masp) REFERENCES SanPham(masp);

go
ALTER TABLE SanPhamSize
ADD CONSTRAINT FK_SizetoSPS
FOREIGN KEY (masize) REFERENCES Size(masize);

go
ALTER TABLE ChiTietDonHang
ADD CONSTRAINT FK_DHtoCTDH
FOREIGN KEY (madh) REFERENCES DonHang(madh);

go
ALTER TABLE ChiTietDonHang
ADD CONSTRAINT FK_SPStoCTDH
FOREIGN KEY (masps) REFERENCES SanPhamSize(masps);

--Thêm dữ liệu
--1 người dùng
go
INSERT INTO NguoiDung (matkhau, hoten, diachi, sdt, email, trangthai,hinh, vaitro)
VALUES ('Btb.123', 'Bùi Thanh Bùi', '123 Đường ABC, Phường XYZ, Quận NNN Cần Thơ, Việt Nam', '0847151739', 
'btb@gmail.com',0, 'btb.png', 0);
INSERT INTO NguoiDung (matkhau, hoten, diachi, sdt, email, trangthai,hinh, vaitro)
VALUES ('nva.123', 'Nguyễn Văn An', '123 Đường ABC, Phường XYZ, Quận NNN Cần Thơ, Việt Nam', '0847151123', 
'nva@gmail.com',0, 'nva.png', 1);
INSERT INTO NguoiDung (matkhau, hoten, diachi, sdt, email, trangthai,hinh, vaitro)
VALUES
('tvb.123', 'Tran Van Binh', '123 Đường ABC, Phường XYZ, Quận NNN Cần Thơ, Việt Nam', '0844576123', 
'nva@gmail.com',0, 'nva.png', 1);
--2 danh mục 
go
INSERT INTO DanhMuc (tendm,anhdm,trangthai) 
VALUES (N'Converse','category1.jpg',0),
	   (N'Vans','category2.jpg',0),
	   (N'Adidas','category3.jpg',0),
	   (N'Nike','category4.jpg',0),
	   (N'Superme','category5.jpg',1);

--3 nhà cung cấp
go
INSERT INTO NhaCungCap Values ('Bitis', 'bitis123@gmail.com', '0946273998', N'22 Lý Chiêu Hoàng, Phường 10, Quận 6, TP. HCM');
INSERT INTO NhaCungCap Values ('Juno', 'cskh@juno.vn', '0956483958', N'313 Nguyễn Thị Thập, Phường Tân Phú, Quận 7, TP. HCM');
INSERT INTO NhaCungCap Values ('Vinagiay', 'cskh@vinagiay.vn', '0957684938', N'180 - 182 Lý Chính Thắng, Quận 3, TP. HCM');
INSERT INTO NhaCungCap Values ('EvaShoes', 'Info@evashoes.com.vn', '0946583875', N'Tầng 1, Số 26 Nguyễn Phong Sắc, Dịch Vọng, Cầu Giấy, Hà Nội');
--4 giảm giá
go
INSERT INTO giamgia Values (N'Tháng Yêu Thương - Bao La Khuyến Mãi', 70, '2023-06-08', '2023-09-01', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
INSERT INTO giamgia Values (N'Bóc Thăm Trúng Thưởng', 50, '2023-06-01', '2023-07-01', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
INSERT INTO giamgia Values (N'Ngày Vàng Thứ Hai', 50, '2023-06-10', '2023-06-20', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
INSERT INTO giamgia Values (N'Sale Độc Nhât ', 40, '2023-06-10', '2023-08-01', N'Chương trình khuyến mãi dành cho quý khi mua giày tại của hàng giày');
--5 size
go
INSERT INTO Size (sizegiay,trangthai) 
VALUES (35,0),
	   (36,0),
	   (37,0),
	   (38,0),
	   (39,1);

--6 sản phẩm
go
INSERT INTO SanPham (tensp,hinhanh1,hinhanh2,hinhanh3,loai,gia,mota,trangthai,madm,mancc,magiamgia) 
VALUES (N'Converse Run Star Hike','anh10-1.png','anh10-2.png','anh10-3.png',0,200,N'Đôi giày Run Star Hike với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn.',0,1,1,1),
	   (N'Converse Renew Canvas','hinh7-1.jpg','hinh7-2.jpg','hinh7-3.jpg',1,300,N'Converse Renew Canvas, phiên bản giới hạn mang mục đích bảo vệ môi trường sẽ được chính thức mở bán tại hệ thống Converse VN từ ngày 5/7 với số lượng giới hạn.',0,1,1,1),
	   (N'Converse Run Star Move','anh9-1.jpg','anh9-2.jpg','anh9-3.jpg',0,500,N'1Đôi giày Run Star Move với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố.',0,1,1,1),
	   (N'Vans Authen DX BW','hinh6-1.jpg','hinh6-2.jpg','hinh6-3.jpg',1,450,N'Đôi giày Run Star Move với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố.',0,2,1,1),
	   (N'Vans SK8-Hi BW','anh11-1.jpg','anh11-2.jpg','anh11-3.jpg',0,900,N'Vans SK8-Hi với thiết kế cổ cao qua mắt cá chân và giữ lại chi tiết lượn sóng đặc trưng 2 bên thân giày. Sử dụng kết hợp cả 2 chất liệu Canvas và da lộn mềm mại giúp form giày ôm chân hơn. ',0,2,1,1);

--7 yêu thích
go
INSERT INTO YeuThich Values ('2023-08-08', 1, 1);
INSERT INTO YeuThich Values ('2023-06-28', 2, 5);
INSERT INTO YeuThich Values ('2023-06-28', 2, 3);
INSERT INTO YeuThich Values ('2023-07-17', 2, 4);
INSERT INTO YeuThich Values ('2023-07-18', 2, 2);
INSERT INTO YeuThich Values ('2023-08-08', 2, 1);

--8 sản phẩm size
go
INSERT INTO SanPhamSize (masp,masize,soluong) 
VALUES (1,1,5),
	   (2,1,15),
	   (3,1,25),
	   (4,2,35),
	   (5,5,45);

--9 giỏ hàng
go
INSERT INTO GioHang(mand) 
VALUES (3),
	   (3),
	   (3),
	   (2),
	   (2);
	   
--10 Giỏ hàng chi tiết
go
INSERT INTO ChiTietGioHang(magh,masps,soluong) 
VALUES (2,3,3);
--11 đơn hàng
go
INSERT INTO DonHang(mand,ngaytao,tongtien,diachigiaohang,nguoinhan,sdtnhanhang,trangthai)
VALUES (2,'2023-06-28',0,N'123 Đường Số 1, Phường An Bình, Quận Ninh Kiều Cần Thơ,',N'Nguyễn Trần Minh Nhân','0943857632',N'Đang Chờ Xác Nhận');
--12 chi tiết đơn hàng
--13 thanh toán

