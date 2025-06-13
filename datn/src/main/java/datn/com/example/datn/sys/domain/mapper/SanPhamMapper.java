package datn.com.example.datn.sys.domain.mapper;

import datn.com.example.datn.sys.domain.dto.SanPhamDto;
import datn.com.example.datn.sys.domain.entity.SanPham;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SanPhamMapper {
    SanPhamMapper INSTANCE = Mappers.getMapper(SanPhamMapper.class);

    SanPhamDto toDto(SanPham sanPham);
    SanPham toEntity(SanPhamDto dto);
    List<SanPhamDto> toDtoList(List<SanPham> list);
}
