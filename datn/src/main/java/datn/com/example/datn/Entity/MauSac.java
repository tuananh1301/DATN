package datn.com.example.datn.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mau_sac")
public class MauSac {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_mau_sac", length = 100)
    private String tenMauSac;

    @Column(name = "ma_hex", length = 10)
    private String maHex;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}