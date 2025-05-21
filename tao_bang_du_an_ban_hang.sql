
-- ========================
-- 1. Bảng Vai Trò & User
-- ========================
CREATE TABLE vai_tro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_vai_tro VARCHAR(100),
    trang_thai BOOLEAN
);

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    ten_dang_nhap VARCHAR(100),
    mat_khau VARCHAR(255),
    so_dien_thoai VARCHAR(20),
    gioi_tinh BOOLEAN,
    ngay_sinh DATE,
    dia_chi TEXT,
    id_vai_tro INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_vai_tro) REFERENCES vai_tro(id)
);

-- ========================
-- 2. Khách Hàng & Địa Chỉ
-- ========================
CREATE TABLE khach_hang (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    so_dien_thoai VARCHAR(20),
    gioi_tinh BOOLEAN,
    ngay_sinh DATE,
    id_user INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE tinh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_tinh VARCHAR(100)
);

CREATE TABLE huyen (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_huyen VARCHAR(100),
    id_tinh INT,
    FOREIGN KEY (id_tinh) REFERENCES tinh(id)
);

CREATE TABLE xa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_xa VARCHAR(100),
    id_huyen INT,
    FOREIGN KEY (id_huyen) REFERENCES huyen(id)
);

CREATE TABLE dia_chi (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_dia_chi TEXT,
    id_khach_hang INT,
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id)
);

-- ========================
-- 3. Danh Mục - Chất Liệu - Xuất Xứ
-- ========================
CREATE TABLE danh_muc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_danh_muc VARCHAR(100),
    trang_thai BOOLEAN
);

CREATE TABLE chat_lieu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_chat_lieu VARCHAR(100),
    trang_thai BOOLEAN
);

CREATE TABLE xuat_xu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    noi_xuat_xu VARCHAR(100),
    trang_thai BOOLEAN
);

-- ========================
-- 4. Sản Phẩm & Chi Tiết
-- ========================
CREATE TABLE san_pham (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ma_san_pham VARCHAR(100),
    ten_san_pham VARCHAR(255),
    mo_ta TEXT,
    id_danh_muc INT,
    id_chat_lieu INT,
    id_xuat_xu INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id),
    FOREIGN KEY (id_chat_lieu) REFERENCES chat_lieu(id),
    FOREIGN KEY (id_xuat_xu) REFERENCES xuat_xu(id)
);

CREATE TABLE mau_sac (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_mau_sac VARCHAR(100),
    ma_hex VARCHAR(10),
    trang_thai BOOLEAN
);

CREATE TABLE kich_thuoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_kich_thuoc VARCHAR(50),
    trang_thai BOOLEAN
);

CREATE TABLE san_pham_chi_tiet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_san_pham INT,
    id_kich_thuoc INT,
    id_mau_sac INT,
    so_luong INT,
    gia_ban DECIMAL(12,2),
    trang_thai BOOLEAN,
    FOREIGN KEY (id_san_pham) REFERENCES san_pham(id),
    FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(id),
    FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id)
);

CREATE TABLE image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    image_url TEXT,
    id_san_pham_chi_tiet INT,
    anh_chinh BOOLEAN,
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id)
);

-- ========================
-- 5. Giỏ Hàng
-- ========================
CREATE TABLE gio_hang (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_khach_hang INT,
    ngay_tao DATETIME,
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id)
);

CREATE TABLE gio_hang_chi_tiet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_gio_hang INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
    FOREIGN KEY (id_gio_hang) REFERENCES gio_hang(id),
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id)
);

-- ========================
-- 6. Hóa Đơn
-- ========================
CREATE TABLE hoa_don (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_khach_hang INT,
    id_user INT,
    ma_hoa_don VARCHAR(100),
    loai_don VARCHAR(50),
    ghi_chu TEXT,
    ho_ten_nguoi_nhan VARCHAR(100),
    so_dien_thoai VARCHAR(20),
    dia_chi TEXT,
    id_ptvc INT,
    tong_tien DECIMAL(12,2),
    ngay_tao DATETIME,
    ngay_thanh_toan DATETIME,
    id_phieu_giam_gia INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id),
    FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE hoa_don_chi_tiet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_hoa_don INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
    don_gia DECIMAL(12,2),
    trang_thai BOOLEAN,
    FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id),
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id)
);

-- ========================
-- 7. Phiếu Giảm Giá
-- ========================
CREATE TABLE phieu_giam_gia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ma_giam_gia VARCHAR(100),
    ten_phieu VARCHAR(100),
    mo_ta TEXT,
    loai_giam VARCHAR(50),
    gia_tri_giam DECIMAL(10,2),
    dieu_kien_giam DECIMAL(10,2),
    giam_toi_da DECIMAL(10,2),
    so_luong INT,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
    trang_thai BOOLEAN
);

-- ========================
-- 8. Thanh Toán
-- ========================
CREATE TABLE hinh_thuc_thanh_toan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_hinh_thuc VARCHAR(100)
);

CREATE TABLE thanh_toan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_hoa_don INT,
    id_hinh_thuc_thanh_toan INT,
    so_tien DECIMAL(12,2),
    ngay_thanh_toan DATETIME,
    FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id),
    FOREIGN KEY (id_hinh_thuc_thanh_toan) REFERENCES hinh_thuc_thanh_toan(id)
);
