package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kich_thuoc")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KichThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_kich_thuoc", length = 50)
    private String tenKichThuoc;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}