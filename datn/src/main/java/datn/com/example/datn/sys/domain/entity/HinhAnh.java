package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "hinh_anh")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HinhAnh {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "duong_dan_hinh_anh")
    private String duongDanHinhAnh;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_san_pham_chi_tiet")
    private SanPhamChiTiet idSanPhamChiTiet;

    @Column(name = "la_hinh_chinh")
    private Boolean laHinhChinh;

}