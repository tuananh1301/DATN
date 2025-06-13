package datn.com.example.datn.sys.domain.mapper;

import datn.com.example.datn.sys.domain.dto.HoaDonDto;
import datn.com.example.datn.sys.domain.entity.HoaDon;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {
    HoaDonDto toDto(HoaDon entity);
    HoaDon toEntity(HoaDonDto dto);
    List<HoaDonDto> toDtoList(List<HoaDon> list);
}
