package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phuong_thuc_van_chuyen")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhuongThucVanChuyen {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_phuong_thuc", length = 100)
    private String tenPhuongThuc;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}