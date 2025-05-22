package datn.com.example.datn.sys.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "quan_huyen")
public class QuanHuyen {
    @Id
    @Column(name = "district_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    private Tinh province;

    @Column(name = "ten_quan_huyen", nullable = false, length = 100)
    private String tenQuanHuyen;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

}