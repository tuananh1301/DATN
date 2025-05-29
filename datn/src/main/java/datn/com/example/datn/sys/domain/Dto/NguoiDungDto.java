package datn.com.example.datn.sys.domain.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NguoiDungDto {
    Integer id;
    String fullName;
    String tenDangNhap;
    String matKhau;
    String soDienThoai;
    String email;
    Set<String> vaiTro;
    Boolean trangThai;
    Instant ngayTao;
    Instant ngaySua;
}
