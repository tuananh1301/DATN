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
@Table(name = "xuat_xu")
public class XuatXu {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "noi_xuat_xu", length = 100)
    private String noiXuatXu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}