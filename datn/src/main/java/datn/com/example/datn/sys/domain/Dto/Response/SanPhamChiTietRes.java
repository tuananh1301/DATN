package datn.com.example.datn.sys.domain.Dto.Response;

import datn.com.example.datn.sys.domain.Entity.KichThuoc;
import datn.com.example.datn.sys.domain.Entity.MauSac;
import datn.com.example.datn.sys.domain.Entity.SanPham;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTietRes {
    Integer id;
    Integer idSanPham;
    Integer idKichThuoc;
    Integer idMauSac;
    Integer soLuong;
    Double giaBan;
}
