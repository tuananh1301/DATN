package datn.com.example.datn.sys.domain.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NguoiDungUpdateRequest {
    String tenDangNhap;
    String matKhau;
    Set<String> vaiTro;
}
