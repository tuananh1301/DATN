package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kich_thuoc")
    private KichThuoc idKichThuoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mau_sac")
    private MauSac idMauSac;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia_ban")
    private Double giaBan;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_sua")
    private Instant ngaySua;

}