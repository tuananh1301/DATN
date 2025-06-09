package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.DiaChiReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Service.DiaChiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/location")
public class DiaChiController {

    DiaChiService diaChiService;

    @PostMapping("/add")
    public ApiResponse<Boolean> createDiaChi(@RequestBody DiaChiReq diaChiReq) {
        Boolean result = true;
            diaChiService.createDiaChi(diaChiReq);
            return ApiResponse.<Boolean>builder().result(result).build();
    }
}