package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mau_sac")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_mau_sac", length = 100)
    private String tenMauSac;

    @Column(name = "ma_hex", length = 10)
    private String maHex;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}