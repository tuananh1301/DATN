package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Service.SanPhamChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/product")
public class SanPhamChiTietController {
    SanPhamChiTietService sanPhamChiTietService;

    @GetMapping("/getDetailProduct/{id}")
    public ApiResponse<List<SanPhamChiTietRes>> getDetailProduct(@PathVariable Integer id) {
        return ApiResponse.<List<SanPhamChiTietRes>>builder()
                .result(sanPhamChiTietService.getSanPhamByIdSanPham(id))
                .build();
    }
}
