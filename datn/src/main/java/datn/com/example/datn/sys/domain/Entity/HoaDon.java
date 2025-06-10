package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang idKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien idNhanVien;

    @Column(name = "ma_hoa_don", length = 100)
    private String maHoaDon;

    @Column(name = "loai_don", length = 50)
    private String loaiDon;

    @Lob
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ho_ten_nguoi_nhan", length = 100)
    private String hoTenNguoiNhan;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_dia_chi")
    private DiaChi idDiaChi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phuong_thuc_van_chuyen")
    private PhuongThucVanChuyen idPhuongThucVanChuyen;

    @Column(name = "phi_van_chuyen")
    private Double phiVanChuyen;

    @Column(name = "tong_tien")
    private Double tongTien;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_phieu_giam_gia")
    private PhieuGiamGia idPhieuGiamGia;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "dia_chi", length = 400)
    private String diaChi;

}