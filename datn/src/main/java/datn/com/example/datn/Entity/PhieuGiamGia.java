package datn.com.example.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ma_giam_gia", length = 100)
    private String maGiamGia;

    @Column(name = "ten_phieu", length = 100)
    private String tenPhieu;

    @Lob
    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "loai_giam", length = 50)
    private String loaiGiam;

    @Column(name = "gia_tri_giam", precision = 10, scale = 2)
    private BigDecimal giaTriGiam;

    @Column(name = "dieu_kien_giam", precision = 12, scale = 2)
    private BigDecimal dieuKienGiam;

    @Column(name = "giam_toi_da", precision = 12, scale = 2)
    private BigDecimal giamToiDa;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}