package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Service.GioHangChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/cartDetail")
public class GioHangChiTietController {
    GioHangChiTietService gioHangChiTietService;

    @PutMapping("/{id}")
    public ApiResponse<Boolean> update(@RequestBody SanPhamChiTietReq req, @PathVariable Integer id) {
        return ApiResponse.<Boolean>builder()
                .result(gioHangChiTietService.updateGioHangChiTiet(id, req))
                .build();
    }
    @DeleteMapping("/deleteGioHangChiTiet/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        gioHangChiTietService.deleteGioHangChiTiet(id);
        return ApiResponse.<String>builder().result("product has been delete").build();
    }
}
