package datn.com.example.datn.sys.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_hoa_don")
    private HoaDon idHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hinh_thuc_thanh_toan")
    private HinhThucThanhToan idHinhThucThanhToan;

    @Column(name = "so_tien", precision = 12, scale = 2)
    private BigDecimal soTien;

    @Column(name = "ngay_thanh_toan")
    private Instant ngayThanhToan;

}