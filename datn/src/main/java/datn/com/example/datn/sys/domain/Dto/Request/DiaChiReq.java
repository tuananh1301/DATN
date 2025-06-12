package datn.com.example.datn.sys.domain.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiaChiReq {

    @NotNull(message = "ID tỉnh không được để trống")
    Integer idTinh;

    @NotBlank(message = "Tên tỉnh không được để trống")
    String tenTinh;

    @NotNull(message = "ID quận/huyện không được để trống")
    Integer idQuanHuyen;

    @NotBlank(message = "Tên quận/huyện không được để trống")
    String tenQuanHuyen;

    @NotBlank(message = "ID phường/xã không được để trống")
    String idPhuongXa;

    @NotBlank(message = "Tên phường/xã không được để trống")
    String tenPhuongXa;

    String chiTietDiaChi;

    Boolean diaChiMacDinh;

    String soDienThoai;

    String email;

    String tenKhachHang;

    LocalDate ngayTao;

}