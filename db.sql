
use master
go
create database nhom4_shopBanGiay
go
use nhom4_shopBanGiay


--1 người dùng
go 
create table NguoiDung(
	MaND bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MatKhau nvarchar(50) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	DiaChi nvarchar(250) NOT NULL,
	SDT varchar(11) NOT NULL,
	Email nvarchar(50) NOT NULL,
	TrangThai bit NOT NULL DEFAULT 0,
	Hinh nvarchar(50) NOT NULL,
	VaiTro nvarchar(50) NOT NULL
)
--2 danh mục 
go
create table DanhMuc(
	MaDM bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenDM nvarchar(50) NOT NULL,
	AnhDM varchar(50) NOT NULL,
	TrangThai bit NOT NULL default 0
)
--3 nhà cung cấp
go 
create table NhaCungCap(
	MaNCC bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenNCC nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	SDT varchar(50) NOT NULL,
	DiaChi nvarchar(250) NOT NULL,
)
--4 giảm giá
go 
create table GiamGia(
	MaGiamGia bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenGiamGia nvarchar(50) NOT NULL,
	GiamGia Float Default 0,
	NgayTao Date NOT NULL,
	NgayKetThuc Date NOT NULL,
	MoTa nvarchar(250) NULL
)
--5 size
go 
create table Size(
	MaSize bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Size int NOT NULL,
	TrangThai bit NOT NULL default 0
)
--6 sản phẩm
go 
create table SanPham(
	MaSP bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenSanPham nvarchar(50) NOT NULL,
	HinhAnh1 nvarchar(50) NOT NULL,
	HinhAnh2 nvarchar(50) NOT NULL,
	HinhAnh3 nvarchar(50) NOT NULL,
	Loai bit not null default 0,
	Gia Float  Default 0,
	MoTa nvarchar(250) NOT NULL,
	TrangThai bit NOT NULL default 0,
	MaDM bigint NOT NULL,
	MaNCC bigint NOT NULL,
	MaGiamGia bigint NULL
)
--7 yêu thích
go 
create table YeuThich(
	MaYeuThich bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	NgayThich Date NOT NULL,
	MaND bigint NOT NULL,
	MaSP bigint NOT NULL
)
--8 sản phẩm size
go 
create table SanPhamSize(
	MaSPS bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaSP bigint NOT NULL,
	MaSize bigint NOT NULL,
	SoLuong bigint not null
)
--9 giỏ hàng
go 
create table GioHang(
	MaGH bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	SoLuong int NOT NULL,
	MaSPS bigint NOT NULL,
	MaND bigint NOT NULL
)
--10 đơn hàng
go 
create table DonHang(
	MaDH bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaND bigint NOT NULL,
	NgayTao Date NOT NULL,
	TongTien Float Default 0,
	DiaChiGiaoHang nvarchar(250) NOT NULL,
	NguoiNhan nvarchar(150) NOT NULL, 
	SdtNhanHang varchar(11) NOT NULL,
	TrangThai nvarchar(50) NOT NULL
)
--11 chi tiết đơn hàng
go 
create table ChiTietDonHang(
	MaCTDH bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaDH bigint NOT NULL,
	MaSPS bigint NOT NULL,
	SoLuong int NOT NULL
)
--12 thanh toán
go
create table ThanhToan(
	MaTT bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaDH bigint NOT NULL,
	NgayTao date NOT NULL,
	PhuongThuc nvarchar(50) NOT NULL,
	TrangThai bit NOT NULL Default 0
)
-- Taoj các khóa duy nhất
go
ALTER TABLE  ThanhToan ADD CONSTRAINT unique1 UNIQUE (MaDH);
go
ALTER TABLE  SanPham_Size ADD CONSTRAINT unique2 UNIQUE (MaSP, MaSize);
go
ALTER TABLE  GioHang ADD CONSTRAINT unique3 UNIQUE (MaSPS);
go
ALTER TABLE  YeuThich ADD CONSTRAINT unique4 UNIQUE (MaSP);
go

--Tạo liên kết các bảng
ALTER TABLE SanPham
ADD CONSTRAINT FK_DMtoSP
FOREIGN KEY (MaDM) REFERENCES DanhMuc(MaDM);

go
ALTER TABLE SanPham
ADD CONSTRAINT FK_NCCtoSP
FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC);

go
ALTER TABLE SanPham
ADD CONSTRAINT FK_GGtoSP
FOREIGN KEY (MaGiamGia) REFERENCES GiamGia(MaGiamGia);

go
ALTER TABLE YeuThich
ADD CONSTRAINT FK_SPtoYT
FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

go
ALTER TABLE YeuThich
ADD CONSTRAINT FK_NDtoYT
FOREIGN KEY (MaND) REFERENCES NguoiDung(MaND);

go
ALTER TABLE ThanhToan
ADD CONSTRAINT FK_DHtoTT
FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH);

go
ALTER TABLE DonHang
ADD CONSTRAINT FK_NDtoDH
FOREIGN KEY (MaND) REFERENCES NguoiDung(MaND);

go
ALTER TABLE GioHang
ADD CONSTRAINT FK_SPStoGH
FOREIGN KEY (MaSPS) REFERENCES SanPham_Size(MaSPS);

go
ALTER TABLE GioHang
ADD CONSTRAINT FK_NDtoGH
FOREIGN KEY (MaND) REFERENCES NguoiDung(MaND);

go
ALTER TABLE SanPham_Size
ADD CONSTRAINT FK_SPtoSPS
FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

go
ALTER TABLE SanPham_Size
ADD CONSTRAINT FK_SizetoSPS
FOREIGN KEY (MaSize) REFERENCES Size(MaSize);

go
ALTER TABLE ChiTietDonHang
ADD CONSTRAINT FK_DHtoCTDH
FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH);

go
ALTER TABLE ChiTietDonHang
ADD CONSTRAINT FK_SPStoCTDH
FOREIGN KEY (MaSPS) REFERENCES SanPham_Size(MaSPS);

--Thêm dữ liệu
--1 người dùng
--2 danh mục 
go
INSERT INTO DanhMuc (TenDM,AnhDM,TrangThai) 
VALUES (N'Converse','category1.jpg',0),
	   (N'Vans','category2.jpg',0),
	   (N'Adidas','category3.jpg',0),
	   (N'Nike','category4.jpg',0),
	   (N'Superme','category5.jpg',1);

--3 nhà cung cấp
--4 giảm giá
--5 size
go
INSERT INTO Size (Size,TrangThai) 
VALUES (35,0),
	   (36,0),
	   (37,0),
	   (38,0),
	   (39,1);

--6 sản phẩm
go
INSERT INTO SanPham (TenSP,HinhAnh1,HinhAnh2,HinhAnh3,Loai,Gia,MoTa,TrangThai,MaDM,MaNCC,MaGiamGia) 
VALUES (N'Converse Run Star Hike','anh10-1.png','anh10-2.png','anh10-3.png',0,N'Đôi giày Run Star Hike với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố. Màu đen trắng tinh tế không kém phần thanh lịch sẽ phối hợp rất tốt với nhiều kiểu outfit hàng ngày.',0,1,1,1),
	   (N'Converse Renew Canvas','hinh7-1.jpg','hinh7-2.jpg','hinh7-3.jpg',1,N'Converse Renew Canvas, phiên bản giới hạn mang mục đích bảo vệ môi trường sẽ được chính thức mở bán tại hệ thống Converse VN từ ngày 5/7 với số lượng giới hạn.',0,1,1,1),
	   (N'Converse Run Star Move','anh9-1.jpg','anh9-2.jpg','anh9-3.jpg',0,N'Đôi giày Run Star Move với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố.',0,1,1,1),
	   (N'Vans Authen DX BW','hinh6-1.jpg','hinh6-2.jpg','hinh6-3.jpg',1,N'Đôi giày Run Star Move với kiểu dáng Chunky thời thượng cùng phong cách độc đáo, mang lại cho bạn vẻ ngoài thu hút ánh nhìn. Đế giày dày dặn cho bạn thỏa sức hack chiều cao và thêm tự tin xuống phố.',0,2,1,1),
	   (N'Vans SK8-Hi BW','anh11-1.jpg','anh11-2.jpg','anh11-3.jpg',0,N'Vans SK8-Hi với thiết kế cổ cao qua mắt cá chân và giữ lại chi tiết lượn sóng đặc trưng 2 bên thân giày. Sử dụng kết hợp cả 2 chất liệu Canvas và da lộn mềm mại giúp form giày ôm chân hơn. ',0,2,1,1);

--7 yêu thích
--8 sản phẩm size
go
INSERT INTO SanPhamSize (MaSP,MaSize,SoLuong) 
VALUES (1,1,5),
	   (2,1,15),
	   (3,1,25),
	   (4,2,35),
	   (5,5,45);

--9 giỏ hàng
--10 đơn hàng
--11 chi tiết đơn hàng
--12 thanh toán

