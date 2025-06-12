package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangRes;
import datn.com.example.datn.sys.domain.Service.GioHangChiTietService;
import datn.com.example.datn.sys.domain.Service.GioHangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/cart")
public class GioHangController {
    GioHangChiTietService gioHangChiTietService;

    GioHangService gioHangService;

    @PostMapping("/add")
    public ApiResponse<Boolean> addCart(@RequestBody GioHangChiTietReq gioHangChiTietReq) {
        var result = gioHangChiTietService.addGioHangChiTiet(gioHangChiTietReq);
        return ApiResponse.<Boolean>builder().result(result).build();
    }

    @GetMapping("/get")
    public ApiResponse<GioHangRes> getCart() {
        var result = gioHangService.getCart();
        return ApiResponse.<GioHangRes>builder().result(result).build();
    }

}
