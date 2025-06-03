package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

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

    @Size(max = 100)
    @Column(name = "ma_hoa_don", length = 100)
    private String maHoaDon;

    @Size(max = 50)
    @Column(name = "loai_don", length = 50)
    private String loaiDon;

    @Lob
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Size(max = 100)
    @Column(name = "ho_ten_nguoi_nhan", length = 100)
    private String hoTenNguoiNhan;

    @Size(max = 20)
    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_dia_chi")
    private DiaChi idDiaChi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phuong_thuc_van_chuyen")
    private PhuongThucVanChuyen idPhuongThucVanChuyen;

    @Column(name = "phi_van_chuyen", precision = 12, scale = 2)
    private BigDecimal phiVanChuyen;

    @Column(name = "tong_tien", precision = 12, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @Column(name = "ngay_sua")
    private Instant ngaySua;

    @Column(name = "ngay_thanh_toan")
    private Instant ngayThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_phieu_giam_gia")
    private PhieuGiamGia idPhieuGiamGia;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Size(max = 400)
    @Column(name = "dia_chi", length = 400)
    private String diaChi;

}