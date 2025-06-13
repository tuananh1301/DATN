package datn.com.example.datn.sys.domain.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import datn.com.example.datn.sys.domain.Dto.Response.VaiTroRes;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NguoiDungReq {
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
