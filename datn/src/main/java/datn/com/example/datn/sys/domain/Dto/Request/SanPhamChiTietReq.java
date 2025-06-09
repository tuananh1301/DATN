package datn.com.example.datn.sys.domain.Dto.Request;

import datn.com.example.datn.sys.domain.Dto.Response.KichThuocRes;
import datn.com.example.datn.sys.domain.Dto.Response.MauSacRes;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamRes;
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
public class SanPhamChiTietReq {
    String maSanPham;
    Integer idKichThuoc;
    Integer idMauSac;
}
