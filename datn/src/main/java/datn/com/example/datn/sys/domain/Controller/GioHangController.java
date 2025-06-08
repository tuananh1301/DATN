package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes;
import datn.com.example.datn.sys.domain.Service.GioHangChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/cart")
public class GioHangChiTietController {
    GioHangChiTietService gioHangChiTietService;

    @PostMapping("/add")
    public ApiResponse<GioHangChiTietRes> addCart(@RequestBody GioHangChiTietReq gioHangChiTietReq) {
        var result = gioHangChiTietService.addGioHangChiTiet(gioHangChiTietReq);
        return ApiResponse.<GioHangChiTietRes>builder().result(result).build();
    }
}
