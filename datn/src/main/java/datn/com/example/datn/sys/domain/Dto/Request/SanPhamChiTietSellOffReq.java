package datn.com.example.datn.sys.domain.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTietSellOffReq {
    String maSanPham;
    Integer idKichThuoc;
    Integer idMauSac;
}
