package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.NguoiDungDto;
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
@RequestMapping("/nguoidung")
public class NguoiDungController {
    NguoiDungService nguoiDungService;

    @PostMapping("/add")
    public ApiResponse<NguoiDungDto> create(@RequestBody NguoiDungDto nguoiDungDto) {
        return ApiResponse.<NguoiDungDto>builder().result(nguoiDungService.create(nguoiDungDto)).build();
    }
}
