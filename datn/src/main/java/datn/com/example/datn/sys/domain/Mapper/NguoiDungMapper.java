package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungReq;
import datn.com.example.datn.sys.domain.Dto.Response.NguoiDungRes;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {
    NguoiDung toNguoiDung(NguoiDungReq nguoiDungReq);
    NguoiDungRes toNguoiDungDto(NguoiDung nguoiDung);
}
