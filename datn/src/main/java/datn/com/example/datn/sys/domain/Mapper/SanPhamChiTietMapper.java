package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.SanPhamChiTietDto;
import datn.com.example.datn.sys.domain.Entity.SanPham;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SanPhamChiTietMapper {
    SanPhamChiTiet toSanPhamChiTiet(SanPhamChiTietDto sanPhamChiTietDto);
    SanPhamChiTietDto toSanPhamChiTietDto(SanPham sanPham);
}
