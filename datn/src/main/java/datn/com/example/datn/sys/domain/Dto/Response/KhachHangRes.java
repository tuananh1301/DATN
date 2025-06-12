package datn.com.example.datn.sys.domain.Dto.Response;

import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhachHangRes { Integer id;
    String tenKhachHang;
    Boolean gioiTinh;
    LocalDate ngaySinh;
    String soDienThoai;
    Integer idNguoiDung;
    Boolean trangThai;
    LocalDate ngayTao;
    LocalDate ngaySua;
}
