package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang idKhachHang;

    @NotNull
    @Column(name = "id_tinh", nullable = false)
    private Integer idTinh;

    @NotNull
    @Column(name = "ten_tinh", nullable = false, length = 100)
    private String tenTinh;

    @NotNull
    @Column(name = "id_quan_huyen", nullable = false)
    private Integer idQuanHuyen;

    @NotNull
    @Column(name = "ten_quan_huyen", nullable = false, length = 100)
    private String tenQuanHuyen;

    @NotNull
    @Column(name = "id_phuong_xa", nullable = false, length = 10)
    private String idPhuongXa;

    @NotNull
    @Column(name = "ten_phuong_xa", nullable = false, length = 100)
    private String tenPhuongXa;

    @Column(name = "chi_tiet_dia_chi")
    private String chiTietDiaChi;

    @ColumnDefault("0")
    @Column(name = "dia_chi_mac_dinh")
    private Boolean diaChiMacDinh;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "ten_khach_hang", length = 255)
    private String tenKhachHang;

}