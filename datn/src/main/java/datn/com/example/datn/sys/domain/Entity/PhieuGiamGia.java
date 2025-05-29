package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "ma_giam_gia", length = 100)
    private String maGiamGia;

    @Size(max = 100)
    @Column(name = "ten_phieu", length = 100)
    private String tenPhieu;

    @Lob
    @Column(name = "mo_ta")
    private String moTa;

    @Size(max = 50)
    @Column(name = "loai_giam", length = 50)
    private String loaiGiam;

    @Column(name = "gia_tri_giam")
    private Double giaTriGiam;

    @Column(name = "dieu_kien_giam")
    private Double dieuKienGiam;

    @Column(name = "giam_toi_da")
    private Double giamToiDa;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "ngay_bat_dau")
    private Instant ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Instant ngayKetThuc;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_sua")
    private Instant ngaySua;

}