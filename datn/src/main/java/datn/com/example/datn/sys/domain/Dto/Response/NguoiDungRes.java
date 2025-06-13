package datn.com.example.datn.sys.domain.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NguoiDungRes {
    String fullName;
    String tenDangNhap;
    String matKhau;
    LocalDate ngaySinh;
    Boolean gioiTinh;
    String soDienThoai;
    String email;
    Set<VaiTroRes> vaiTro;
    Boolean trangThai;
    LocalDate ngayTao;
    LocalDate ngaySua;
}
