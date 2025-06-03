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
@Table(name = "hinh_thuc_thanh_toan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HinhThucThanhToan {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_hinh_thuc", length = 100)
    private String tenHinhThuc;

}