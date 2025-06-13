package datn.com.example.datn.sys.domain.Mapper;

import datn.com.example.datn.sys.domain.Dto.Request.DiaChiReq;
import datn.com.example.datn.sys.domain.Entity.DiaChi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface DiaChiMapper {
    @Mapping(target = "ngayTao", ignore = true)
    @Mapping(target = "idKhachHang", ignore = true)
    @Mapping(target = "ngaySua", ignore = true)
    @Mapping(target = "trangThai", constant = "true")
    DiaChi toEntity(DiaChiReq diaChiReq);
}
