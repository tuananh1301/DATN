package datn.com.example.datn.sys.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KichThuocDto {
    private Integer id;
    private String tenKichThuoc;
    private Boolean trangThai;
}