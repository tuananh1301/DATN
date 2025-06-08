package datn.com.example.datn.sys.domain.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GioHangChiTietRes {

    String tenSanPham;

    Integer idSanPhamChiTiet;

    Double giaBan;
    Integer soLuongTrongGio;
    Integer soLuongSanPham;
    Double giaBanTrongGio;
    String tenKichThuoc;
    String tenMauSac;
    String duongDanHinhAnh;

    LocalDate ngayTao;
}
