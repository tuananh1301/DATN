package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "hinh_thuc_thanh_toan")
public class HinhThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "ten_hinh_thuc", length = 100)
    private String tenHinhThuc;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_sua")
    private Instant ngaySua;

}