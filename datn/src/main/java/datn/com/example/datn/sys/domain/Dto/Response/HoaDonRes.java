package datn.com.example.datn.sys.domain.Dto.Response;

import datn.com.example.datn.sys.domain.Entity.*;
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
public class HoaDonRes {
    Integer id;

    Integer idKhachHang;

    Integer idNhanVien;

    String maHoaDon;

    String loaiDon;

    String ghiChu;

    String hoTenNguoiNhan;

    String soDienThoai;

    DiaChi idDiaChi;

    Integer idPhuongThucVanChuyen;

    Double phiVanChuyen;

    Double tongTien;

    LocalDate ngayTao;

    LocalDate ngaySua;

    LocalDate ngayThanhToan;

    Integer idPhieuGiamGia;

    Boolean trangThai;

    String diaChi;
}
