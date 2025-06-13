package datn.com.example.datn.sys.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamDto {
    private Long id;
    private String tenSanPham;
    private String moTa;
    private Boolean trangThai;
}