package datn.com.example.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_nguoi_dung")
    private NguoiDung idNguoiDung;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    private Tinh province;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    private QuanHuyen district;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ward_code", nullable = false)
    private PhuongXa wardCode;

    @Column(name = "chi_tiet_dia_chi")
    private String chiTietDiaChi;

    @ColumnDefault("0")
    @Column(name = "la_dia_chi_mac_dinh")
    private Boolean laDiaChiMacDinh;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

}