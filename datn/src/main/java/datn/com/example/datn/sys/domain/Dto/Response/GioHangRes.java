package datn.com.example.datn.sys.domain.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GioHangRes {
    Integer idGioHang;
    List<GioHangChiTietRes> gioHangChiTietRes;
}
