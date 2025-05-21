package datn.com.example.datn.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tinh")
public class Tinh {
    @Id
    @Column(name = "province_id", nullable = false)
    private Integer id;

    @Column(name = "ten_tinh", nullable = false, length = 100)
    private String tenTinh;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

}