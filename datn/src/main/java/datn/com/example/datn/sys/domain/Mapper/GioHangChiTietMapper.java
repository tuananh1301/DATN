package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes;
import datn.com.example.datn.sys.domain.Entity.GioHang;
import datn.com.example.datn.sys.domain.Entity.GioHangChiTiet;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GioHangChiTietMapper {
    @Mapping(target = "idGioHang", source = "idGioHang", qualifiedByName = "mapGioHang1" )
    @Mapping(target = "idSanPhamChiTiet", source = "idSanPhamChiTiet", qualifiedByName = "mapSPCT1")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ngaySua", ignore = true)
    GioHangChiTiet toEntity(GioHangChiTietReq req);
//    @Mapping(source = "idGioHang", target = "idGioHang",  qualifiedByName = "mapGioHang" )
    @Mapping(target = "idSanPhamChiTiet", qualifiedByName = "mapSPCT")
    GioHangChiTietRes toResponse(GioHangChiTiet entity);
    @Named("mapGioHang1")
    default GioHang mapGioHang1(Integer idGioHang){
        GioHang gioHang = new GioHang();
        gioHang.setId(idGioHang);
        return gioHang;
    }
    @Named("mapSPCT1")
    default SanPhamChiTiet mapSPCT1(Integer idSanPhamChiTiet){
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setId(idSanPhamChiTiet);
        return sanPhamChiTiet;
    }
    @Named("mapGioHang")
    default Integer mapGioHang(GioHang gioHang){
        return gioHang.getId();
    }
    @Named("mapSPCT")
    default Integer mapSPCT(SanPhamChiTiet sanPhamChiTiet){
        return sanPhamChiTiet.getId();
    }

}
