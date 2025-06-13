package datn.com.example.datn.sys.domain.Dto.Response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MauSacRes {
    Integer id;

    String tenMauSac;

    String maHex;

    Boolean trangThai;

    LocalDate ngayTao;

    LocalDate ngaySua;
}
