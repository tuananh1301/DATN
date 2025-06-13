package datn.com.example.datn.sys.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamChiTietDto {
    private Integer id;
    private String ma;
    private Integer soLuong;
    private BigDecimal giaBan;
    private Integer idSanPham;
    private Integer idKichThuoc;
    private Integer idMauSac;
}
