package datn.com.example.datn.sys.domain.Dto.Response;

import datn.com.example.datn.sys.domain.Entity.HoaDon;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTietRes {

    Integer id;

    Integer idHoaDon;

    SanPhamChiTietRes idSanPhamChiTiet;

    Integer soLuong;

    Double donGia;

    Boolean trangThai;

    LocalDate ngayTao;

    LocalDate ngaySua;
}
