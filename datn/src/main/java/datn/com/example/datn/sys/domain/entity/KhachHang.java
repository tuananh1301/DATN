package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "khach_hang")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KhachHang {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ho_ten", length = 100)
    private String hoTen;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_nguoi_dung")
    private NguoiDung idNguoiDung;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}