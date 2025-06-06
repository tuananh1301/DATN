package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungReq;
import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungUpdateRequest;
import datn.com.example.datn.sys.domain.Dto.Response.NguoiDungRes;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {
    NguoiDung toNguoiDung(NguoiDungReq nguoiDungReq);
    NguoiDungRes toNguoiDungDto(NguoiDung nguoiDung);
    @Mapping(target = "vaiTro", ignore = true)
    void toUpdateNguoiDung(@MappingTarget NguoiDung nguoiDung, NguoiDungUpdateRequest nguoiDungUpdateRequest);
}
