package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Service.BanHangOffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/off/sell")
public class BanHangOffController {
    BanHangOffService banHangOffService;
    @GetMapping("/getSPCT")
    public ApiResponse<List<SanPhamChiTietRes>> getSPCT(@RequestBody SanPhamChiTietSellOffReq request) {
        return ApiResponse.<List<SanPhamChiTietRes>>builder()
                .result(banHangOffService.findAllSanPhamChiTietsByMaSanPham(request))
                .build();
    }
}
