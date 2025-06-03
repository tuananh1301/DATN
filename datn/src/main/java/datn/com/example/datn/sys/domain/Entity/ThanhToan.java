package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "thanh_toan")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_hoa_don")
    private HoaDon idHoaDon;

    @Column(name = "so_tien", precision = 12, scale = 2)
    private BigDecimal soTien;

    @Column(name = "ngay_thanh_toan")
    private Instant ngayThanhToan;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_sua")
    private Instant ngaySua;

    @Size(max = 100)
    @Column(name = "ten_hinh_thuc_thanh_toan", length = 100)
    private String tenHinhThucThanhToan;

}