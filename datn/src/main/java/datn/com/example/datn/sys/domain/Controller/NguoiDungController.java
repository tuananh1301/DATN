package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.NguoiDungRes;
import datn.com.example.datn.sys.domain.Service.NguoiDungService;
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
@RequestMapping("/users")
public class NguoiDungController {
    NguoiDungService nguoiDungService;

    @PostMapping("/createCustomer")
    public ApiResponse<NguoiDungRes> createCustomer(@RequestBody NguoiDungReq nguoiDungReq) {
        return ApiResponse.<NguoiDungRes>builder().result(nguoiDungService.createCustomer(nguoiDungReq)).build();
    }
    @PostMapping("/createStaff")
    public ApiResponse<NguoiDungRes> createStaff(@RequestBody NguoiDungReq nguoiDungReq) {
        return ApiResponse.<NguoiDungRes>builder().result(nguoiDungService.createStaff(nguoiDungReq)).build();
    }
}
