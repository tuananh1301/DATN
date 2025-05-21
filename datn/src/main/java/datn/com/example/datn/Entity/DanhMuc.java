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
@Table(name = "danh_muc")
public class DanhMuc {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_danh_muc", length = 100)
    private String tenDanhMuc;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}