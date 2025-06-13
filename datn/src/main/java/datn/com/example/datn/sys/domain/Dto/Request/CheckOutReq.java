package datn.com.example.datn.sys.domain.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckOutReq {
    Integer idKhachHang;
    Integer idHoaDon;
    Double tongTien;
    String tenHinhThucThanhToan;
}
