package datn.com.example.datn.sys.domain.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonDto {
    private Integer id;
    private String ma;
    private LocalDateTime ngayTao;
    private String trangThai;
}
