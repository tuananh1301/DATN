package datn.com.example.datn.sys.domain.Dto.Request;

import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTietReq {

    Integer id;

    Integer idHoaDon;

    SanPhamChiTietRes idSanPhamChiTiet;

    Integer soLuong;

    Double donGia;

    Boolean trangThai;

    LocalDate ngayTao;

    LocalDate ngaySua;
}
