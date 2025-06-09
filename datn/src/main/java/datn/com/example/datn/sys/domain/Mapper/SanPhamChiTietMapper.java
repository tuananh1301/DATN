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
    SanPhamChiTiet toSanPhamChiTiet(SanPhamChiTietReq sanPhamChiTietReq);
    SanPhamChiTietRes toSanPhamChiTietRes(SanPhamChiTiet sanPhamChiTiet);
}
