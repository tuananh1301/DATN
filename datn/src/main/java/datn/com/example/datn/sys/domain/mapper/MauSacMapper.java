package datn.com.example.datn.sys.domain.mapper;

import datn.com.example.datn.sys.domain.dto.MauSacDto;
import datn.com.example.datn.sys.domain.entity.MauSac;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MauSacMapper {
    MauSacDto toDto(MauSac entity);
    MauSac toEntity(MauSacDto dto);
    List<MauSacDto> toDtoList(List<MauSac> list);
}