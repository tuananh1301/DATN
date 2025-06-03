package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "san_pham_chi_tiet")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_san_pham")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SanPham idSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kich_thuoc")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private KichThuoc idKichThuoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mau_sac")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MauSac idMauSac;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia_ban", precision = 12, scale = 2)
    private BigDecimal giaBan;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
