package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.HoaDonReq;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonRes;
import datn.com.example.datn.sys.domain.Entity.HoaDon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {
    HoaDon toHoaDon(HoaDonReq hoaDonReq);

    @Mapping(target = "idNhanVien", ignore = true)
    @Mapping(target = "idKhachHang", ignore = true)
    @Mapping(target = "idPhuongThucVanChuyen", ignore = true)
    @Mapping(target = "idPhieuGiamGia", ignore = true)
    HoaDonRes toHoaDonRes(HoaDon hoaDon);
}
