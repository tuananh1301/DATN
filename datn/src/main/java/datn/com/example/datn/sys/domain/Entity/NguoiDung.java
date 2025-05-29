package datn.com.example.datn.sys.domain.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
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
    Integer id;

    @Column(name = "fullname")
    String fullName;

    @Size(max = 100)
    @Column(name = "ten_dang_nhap", length = 100)
    String tenDangNhap;

    @Size(max = 255)
    @Column(name = "mat_khau")
    String matKhau;

    @Size(max = 20)
    @Column(name = "so_dien_thoai", length = 20)
    String soDienThoai;

    @Size(max = 250)
    @Column(name = "email", length = 250)
    String email;

    @Size(max = 50)
    @Column(name = "vai_tro", length = 50)
    Set<String> vaiTro;

    @Column(name = "trang_thai")
    Boolean trangThai;

    @Column(name = "ngay_tao")
    Instant ngayTao;

    @Column(name = "ngay_sua")
    Instant ngaySua;

}