package datn.com.example.datn.sys.domain.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HienThiSanPhamDto {
    Integer id;
    String tenSanPham;
    BigDecimal giaBan;
    String duongDanHinhAnh;

}
