package datn.com.example.datn.sys.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauSacDto {
    private Integer id;
    private String tenMauSac;
    private String maHex;
    private Boolean trangThai;
}