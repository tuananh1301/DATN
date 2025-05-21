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
@Table(name = "kich_thuoc")
public class KichThuoc {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_kich_thuoc", length = 50)
    private String tenKichThuoc;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}