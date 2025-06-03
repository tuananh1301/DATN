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
@Table(name = "vai_tro")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VaiTro {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_vai_tro", length = 100)
    private String tenVaiTro;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}