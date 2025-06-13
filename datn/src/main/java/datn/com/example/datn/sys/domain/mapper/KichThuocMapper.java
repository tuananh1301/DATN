package datn.com.example.datn.sys.domain.mapper;

import datn.com.example.datn.sys.domain.dto.KichThuocDto;
import datn.com.example.datn.sys.domain.entity.KichThuoc;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface KichThuocMapper {
    KichThuocDto toDto(KichThuoc entity);
    KichThuoc toEntity(KichThuocDto dto);
    List<KichThuocDto> toDtoList(List<KichThuoc> list);
}