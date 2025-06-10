package datn.com.example.datn.sys.domain.Dto.Request;

import datn.com.example.datn.sys.domain.Entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonReq {
    String loaiDon;

    String ghiChu;

    String hoTenNguoiNhan;

    String soDienThoai;

    Double phiVanChuyen;

    Double tongTien;

    LocalDate ngaySua;

    LocalDate ngayThanhToan;

    Boolean trangThai;

    String diaChi;
}
