package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.HoaDonChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.HoaDonReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonChiTietRes;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonRes;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Entity.HoaDon;
import datn.com.example.datn.sys.domain.Service.BanHangOffService;
import datn.com.example.datn.sys.domain.Service.HoaDonChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/off/sell")
public class BanHangOffController {

    BanHangOffService banHangOffService;
    HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/getSPCT")
    public ApiResponse<SanPhamChiTietRes> getSPCT(@RequestBody SanPhamChiTietSellOffReq request) {
        return ApiResponse.<SanPhamChiTietRes>builder()
                .result(banHangOffService.findAllSanPhamChiTietsByMaSanPham(request))
                .build();
    }
    @PostMapping("/createHoaDon")
    public ApiResponse<HoaDonRes> createHoaDon(@RequestBody HoaDonReq req) {
        var result = banHangOffService.createHoaDon(req);
        return ApiResponse.<HoaDonRes>builder().result(result).build();
    }
    @PostMapping("/createHoaDonChiTiet")
    public ApiResponse<HoaDonChiTietRes> createHoaDonChiTiet(@RequestBody HoaDonChiTietReq req) {
        var result = banHangOffService.createHoaDonChiTiet(req);
        return ApiResponse.<HoaDonChiTietRes>builder().result(result).build();
    }
    @DeleteMapping("/deleteHoaDonChiTiet/{idHoaDonChiTiet}")
    public ApiResponse<String> deleteHoaDonChiTiet(@PathVariable Integer idHoaDonChiTiet) {
        hoaDonChiTietService.delete(idHoaDonChiTiet);
        return ApiResponse.<String>builder().result("Bill has been deleted").build();
    }
}
