package datn.com.example.datn.sys.domain.mapper;

import datn.com.example.datn.sys.domain.dto.SanPhamChiTietDto;
import datn.com.example.datn.sys.domain.entity.SanPhamChiTiet;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SanPhamChiTietMapper {
    SanPhamChiTietDto toDto(SanPhamChiTiet entity);

    @Mapping(target = "sanPham.id", source = "idSanPham")
    @Mapping(target = "kichThuoc.id", source = "idKichThuoc")
    @Mapping(target = "mauSac.id", source = "idMauSac")
    SanPhamChiTiet toEntity(SanPhamChiTietDto dto);

    List<SanPhamChiTietDto> toDtoList(List<SanPhamChiTiet> list);
}