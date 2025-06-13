package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.HoaDonChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonChiTietRes;
import datn.com.example.datn.sys.domain.Entity.HoaDon;
import datn.com.example.datn.sys.domain.Entity.HoaDonChiTiet;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HoaDonChiTietMapper {
    @Mapping(target = "idHoaDon", source = "idHoaDon", qualifiedByName = "mapHoaDon1")
    HoaDonChiTiet toHoaDonChiTiet(HoaDonChiTietReq request);
    @Mapping(target = "idHoaDon", source = "idHoaDon", qualifiedByName = "mapHoaDon")
    HoaDonChiTietRes toHoaDonChiTietRes(HoaDonChiTiet hoaDonChiTiet);
    @Mapping(target = "idHoaDon", source = "idHoaDon", qualifiedByName = "mapHoaDon1")
    HoaDonChiTiet toHoaDonCT(HoaDonChiTietRes hoaDonChiTietRes);


    @Named("mapHoaDon")
    default Integer mapHoaDon(HoaDon hoaDon) {
        return hoaDon.getId();
    }
    @Named("mapHoaDon1")
    default HoaDon hoaDon1(Integer id) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(id);
        return hoaDon;
    }
}
