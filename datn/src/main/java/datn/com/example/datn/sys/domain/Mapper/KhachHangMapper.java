package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Response.KhachHangRes;
import datn.com.example.datn.sys.domain.Entity.KhachHang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {
    @Mapping(target = "idNguoiDung", ignore = true)
    KhachHangRes toKhachHang(KhachHang khachHang);
}
