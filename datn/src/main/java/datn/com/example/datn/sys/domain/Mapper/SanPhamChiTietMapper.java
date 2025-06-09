package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Entity.KichThuoc;
import datn.com.example.datn.sys.domain.Entity.MauSac;
import datn.com.example.datn.sys.domain.Entity.SanPham;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SanPhamChiTietMapper {
    @Mapping(target = "idSanPham", source = "idSanPham", qualifiedByName = "mapSP")
    @Mapping(target = "idKichThuoc", source = "idKichThuoc", qualifiedByName = "mapKT")
    @Mapping(target = "idMauSac", source = "idMauSac", qualifiedByName = "mapMS")
    SanPhamChiTiet toSanPhamChiTiet(SanPhamChiTietReq sanPhamChiTietReq);

    @Mapping(target = "idSanPham", source = "idSanPham", qualifiedByName = "mapSP1")
    @Mapping(target = "idKichThuoc", source = "idKichThuoc", qualifiedByName = "mapKT1")
    @Mapping(target = "idMauSac", source = "idMauSac", qualifiedByName = "mapMS1")
    SanPhamChiTietRes toSanPhamChiTietRes(SanPhamChiTiet sanPhamChiTiet);

    @Named("mapSP")
    default SanPham mapSP(Integer idSanPham){
        SanPham sanPham = new SanPham();
        sanPham.setId(idSanPham);
        return sanPham;
    }
    @Named("mapKT")
    default KichThuoc mapKT(Integer idKichThuoc){
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setId(idKichThuoc);
        return kichThuoc;
    }
    @Named("mapMS")
    default MauSac mapMS(Integer idMauSac){
        MauSac mauSac = new MauSac();
        mauSac.setId(idMauSac);
        return mauSac;
    }
    @Named("mapSP1")
    default Integer mapSP1(SanPham sanPham){
        return sanPham.getId();
    }
    @Named("mapKT1")
    default Integer mapKT1(KichThuoc kichThuoc){
        return kichThuoc.getId();
    }
    @Named("mapMS1")
    default Integer mapMS1(MauSac mauSac){
        return mauSac.getId();
    }
}
