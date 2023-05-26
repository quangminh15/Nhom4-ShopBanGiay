
use master
go
create database nhom4_shopBanGiay
go
use nhom4_shopBanGiay
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

go
create table DanhMuc(
	MaDM bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenDanhMuc nvarchar(50) NOT NULL,
	AnhDM nvarchar(50) NOT NULL,
)

go 
create table SanPham(
	MaSP bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenSanPham nvarchar(50) NOT NULL,
	HinhAnh1 nvarchar(50) NOT NULL,
	HinhAnh2 nvarchar(50) NOT NULL,
	HinhAnh3 nvarchar(50) NOT NULL,
	Gia Float  Default 0,
	MoTa nvarchar(250) NOT NULL,
	TrangThai bit NOT NULL,
	MaDM bigint NOT NULL,
	MaNCC bigint NOT NULL,
	MaGiamGia bigint NULL
)

go 
create table Size(
	MaSize bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Size nvarchar(50) NOT NULL,
)

go 
create table SanPham_Size(
	MaSPS bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaSP bigint NOT NULL,
	MaSize bigint NOT NULL
)

go 
create table NhaCungCap(
	MaNCC bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenNCC nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	SDT varchar(50) NOT NULL,
	DiaChi nvarchar(250) NOT NULL,
)

go 
create table GiamGia(
	MaGiamGia bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenGiamGia nvarchar(50) NOT NULL,
	GiamGia Float Default 0,
	NgayTao Date NOT NULL,
	MoTa nvarchar(250) NULL
)

go 
create table YeuThich(
	MaYeuThich bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	NgayThich Date NOT NULL,
	MaND bigint NOT NULL,
	MaSP bigint NOT NULL
)

go 
create table GioHang(
	MaGH bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	SoLuong int NOT NULL,
	MaSPS bigint NOT NULL,
	MaND bigint NOT NULL
)

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

go 
create table ChiTietDonHang(
	MaCTDH bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaDH bigint NOT NULL,
	MaSPS bigint NOT NULL,
	SoLuong int NOT NULL,
	ThanhTien Float Default 0
)

go
create table ThanhToan(
	MaTT bigint PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaDH bigint NOT NULL,
	NgayTao date NOT NULL,
	PhuongThuc nvarchar(50) NOT NULL,
	TrangThai bit NOT NULL Default 0
)

go
ALTER TABLE  ThanhToan ADD CONSTRAINT unique1 UNIQUE (MaDH);
go
ALTER TABLE  SanPham_Size ADD CONSTRAINT unique2 UNIQUE (MaSP, MaSize);
go
ALTER TABLE  GioHang ADD CONSTRAINT unique3 UNIQUE (MaSPS);
go
ALTER TABLE  YeuThich ADD CONSTRAINT unique4 UNIQUE (MaSP);
go


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