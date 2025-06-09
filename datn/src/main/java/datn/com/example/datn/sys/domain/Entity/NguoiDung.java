package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "ten_dang_nhap", length = 100)
    String tenDangNhap;

    @Column(name = "mat_khau")
    String matKhau;

    @Column(name = "so_dien_thoai", length = 20)
    String soDienThoai;

    @Column(name = "email", length = 250)
    String email;

    @Column(name = "vai_tro", length = 50)
    Set<String> vaiTro;

    @Column(name = "trang_thai")
    Boolean trangThai;

    @Column(name = "ngay_tao")
    Instant ngayTao;

    @Column(name = "ngay_sinh")
    LocalDate ngaySinh;

    @Column(name = "gioi_tinh")
    Boolean gioiTinh;
}