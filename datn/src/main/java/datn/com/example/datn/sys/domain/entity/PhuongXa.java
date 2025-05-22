package datn.com.example.datn.sys.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "phuong_xa")
public class PhuongXa {
    @Id
    @Column(name = "ward_code", nullable = false, length = 10)
    private String wardCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    private QuanHuyen district;

    @Column(name = "ten_phuong_xa", nullable = false, length = 100)
    private String tenPhuongXa;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

}