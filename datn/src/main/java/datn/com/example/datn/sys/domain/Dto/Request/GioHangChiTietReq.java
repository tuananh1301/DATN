package datn.com.example.datn.sys.domain.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GioHangChiTietReq {
    Integer idGioHang;

    Integer idSanPham;

    Integer idSanPhamChiTiet;

    Integer idMauSac;

    Integer idKichThuoc;

    Integer soLuong;

    LocalDate ngayTao;
}
