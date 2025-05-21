CREATE DATABASE TienSchema1;
USE TienSchema1;

-- ========================
-- 1. Vai Trò & Người Dùng
-- ========================
CREATE TABLE vai_tro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_vai_tro VARCHAR(100),
    trang_thai BOOLEAN
);

CREATE TABLE nguoi_dung (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    ten_dang_nhap VARCHAR(100),
    mat_khau VARCHAR(255),
    so_dien_thoai VARCHAR(20),
    gioi_tinh BOOLEAN,
    ngay_sinh DATE,
    id_vai_tro INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_vai_tro) REFERENCES vai_tro(id) ON DELETE RESTRICT
);

-- ========================
-- 2. Bảng Địa Chỉ (Tích hợp với API GHN)
-- ========================
CREATE TABLE tinh (
    province_id INT PRIMARY KEY, -- ProvinceID từ GHN
    ten_tinh VARCHAR(100) NOT NULL,
    ngay_cap_nhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE quan_huyen (
    district_id INT PRIMARY KEY, -- DistrictID từ GHN
    province_id INT NOT NULL,
    ten_quan_huyen VARCHAR(100) NOT NULL,
    ngay_cap_nhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (province_id) REFERENCES tinh(province_id) ON DELETE RESTRICT
);

CREATE TABLE phuong_xa (
    ward_code VARCHAR(10) PRIMARY KEY, -- WardCode từ GHN
    district_id INT NOT NULL,
    ten_phuong_xa VARCHAR(100) NOT NULL,
    ngay_cap_nhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (district_id) REFERENCES quan_huyen(district_id) ON DELETE RESTRICT
);

CREATE TABLE dia_chi (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_nguoi_dung INT, -- Liên kết với nguoi_dung
    province_id INT NOT NULL,
    district_id INT NOT NULL,
    ward_code VARCHAR(10) NOT NULL,
    chi_tiet_dia_chi VARCHAR(255), -- Số nhà, tên đường
    la_dia_chi_mac_dinh BOOLEAN DEFAULT FALSE, -- Địa chỉ mặc định
    ngay_tao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ngay_cap_nhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
    FOREIGN KEY (province_id) REFERENCES tinh(province_id) ON DELETE RESTRICT,
    FOREIGN KEY (district_id) REFERENCES quan_huyen(district_id) ON DELETE RESTRICT,
    FOREIGN KEY (ward_code) REFERENCES phuong_xa(ward_code) ON DELETE RESTRICT
);

-- ========================
-- 3. Khách Hàng
-- ========================
CREATE TABLE khach_hang (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ho_ten VARCHAR(100),
    so_dien_thoai VARCHAR(20),
    gioi_tinh BOOLEAN,
    ngay_sinh DATE,
    id_nguoi_dung INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id) ON DELETE CASCADE
);

-- ========================
-- 4. Danh Mục - Chất Liệu - Xuất Xứ
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
-- 5. Sản Phẩm & Chi Tiết
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
    FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_chat_lieu) REFERENCES chat_lieu(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_xuat_xu) REFERENCES xuat_xu(id) ON DELETE RESTRICT
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
    FOREIGN KEY (id_san_pham) REFERENCES san_pham(id) ON DELETE CASCADE,
    FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id) ON DELETE RESTRICT
);

CREATE TABLE hinh_anh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    duong_dan_hinh_anh TEXT,
    id_san_pham_chi_tiet INT,
    la_hinh_chinh BOOLEAN,
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id) ON DELETE CASCADE
);

-- ========================
-- 6. Giỏ Hàng
-- ========================
CREATE TABLE gio_hang (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_khach_hang INT,
    ngay_tao DATETIME,
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id) ON DELETE CASCADE
);

CREATE TABLE gio_hang_chi_tiet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_gio_hang INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
    FOREIGN KEY (id_gio_hang) REFERENCES gio_hang(id) ON DELETE CASCADE,
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id) ON DELETE RESTRICT
);

-- ========================
-- 7. Phương thức vận chuyển
-- ========================
CREATE TABLE phuong_thuc_van_chuyen (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_phuong_thuc VARCHAR(100), -- Ví dụ: GHN, GHTK
    service_id INT, -- service_id từ API GHN
    trang_thai BOOLEAN
);

-- ========================
-- 8. Phiếu Giảm Giá
-- ========================
CREATE TABLE phieu_giam_gia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ma_giam_gia VARCHAR(100),
    ten_phieu VARCHAR(100),
    mo_ta TEXT,
    loai_giam VARCHAR(50),
    gia_tri_giam DECIMAL(10,2),
    dieu_kien_giam DECIMAL(12,2),
    giam_toi_da DECIMAL(12,2),
    so_luong INT,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
    trang_thai BOOLEAN
);

-- ========================
-- 9. Hóa Đơn
-- ========================
CREATE TABLE hoa_don (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_khach_hang INT,
    id_nguoi_dung INT, -- Nhân viên xử lý
    ma_hoa_don VARCHAR(100),
    loai_don VARCHAR(50),
    ghi_chu TEXT,
    ho_ten_nguoi_nhan VARCHAR(100), -- Lưu nếu người nhận khác khách hàng
    so_dien_thoai VARCHAR(20),
    id_dia_chi INT, -- Địa chỉ giao hàng (liên kết với dia_chi)
    id_phuong_thuc_van_chuyen INT, -- Phương thức vận chuyển
    phi_van_chuyen DECIMAL(12,2), -- Phí vận chuyển (tính từ API GHN)
    tong_tien DECIMAL(12,2),
    ngay_tao DATETIME,
    ngay_thanh_toan DATETIME,
    id_phieu_giam_gia INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id) ON DELETE SET NULL,
    FOREIGN KEY (id_dia_chi) REFERENCES dia_chi(id) ON DELETE SET NULL,
    FOREIGN KEY (id_phuong_thuc_van_chuyen) REFERENCES phuong_thuc_van_chuyen(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_phieu_giam_gia) REFERENCES phieu_giam_gia(id) ON DELETE SET NULL
);

CREATE TABLE hoa_don_chi_tiet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_hoa_don INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
    don_gia DECIMAL(12,2),
    trang_thai BOOLEAN,
    FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id) ON DELETE CASCADE,
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id) ON DELETE RESTRICT
);

-- ========================
-- 10. Thanh Toán
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
    FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id) ON DELETE CASCADE,
    FOREIGN KEY (id_hinh_thuc_thanh_toan) REFERENCES hinh_thuc_thanh_toan(id) ON DELETE RESTRICT
);

-- ========================
-- 11. Thêm chỉ số (Index) cho hiệu suất
-- ========================
CREATE INDEX idx_id_nguoi_dung ON dia_chi(id_nguoi_dung);
CREATE INDEX idx_id_khach_hang ON hoa_don(id_khach_hang);
CREATE INDEX idx_id_dia_chi ON hoa_don(id_dia_chi);
CREATE INDEX idx_id_hoa_don ON hoa_don_chi_tiet(id_hoa_don);
CREATE INDEX idx_id_san_pham_chi_tiet ON hoa_don_chi_tiet(id_san_pham_chi_tiet);
CREATE INDEX idx_id_gio_hang ON gio_hang_chi_tiet(id_gio_hang);
