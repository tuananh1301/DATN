package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.HienThiSanPhamDto;
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
@RequestMapping("/api/home")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TrangChuController {
    SanPhamChiTietService sanPhamChiTietService;

    @GetMapping("/sanpham")
    public ApiResponse<List<HienThiSanPhamDto>> trangChu() {
        return ApiResponse.<List<HienThiSanPhamDto>>builder().result(sanPhamChiTietService.homeSanPham()).build();
    }

    @GetMapping("/getByDanhMuc/{id}")
    public ApiResponse<List<HienThiSanPhamDto>> getByDanhMuc(@PathVariable Integer id) {
        return ApiResponse.<List<HienThiSanPhamDto>>builder().result(sanPhamChiTietService.getSanPhamByIdDanhMuc(id)).build();
    }

}
