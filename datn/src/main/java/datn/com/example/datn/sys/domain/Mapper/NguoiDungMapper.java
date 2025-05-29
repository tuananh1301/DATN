package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.NguoiDungDto;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {
    NguoiDung toNguoiDung(NguoiDungDto nguoiDungDto);
    NguoiDungDto toNguoiDungDto(NguoiDung nguoiDung);
}
