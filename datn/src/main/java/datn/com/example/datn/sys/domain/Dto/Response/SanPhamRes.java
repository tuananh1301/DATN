package datn.com.example.datn.sys.domain.Dto.Response;

import datn.com.example.datn.sys.domain.Entity.ChatLieu;
import datn.com.example.datn.sys.domain.Entity.DanhMuc;
import datn.com.example.datn.sys.domain.Entity.XuatXu;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamRes {
    Integer id;

    String maSanPham;

    String tenSanPham;

    String moTa;

    Boolean trangThai;

    LocalDate ngayTao;

    LocalDate ngaySua;
}
