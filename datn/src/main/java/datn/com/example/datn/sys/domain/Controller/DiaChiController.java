package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.Request.DiaChiReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Service.DiaChiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/location")
public class DiaChiController {

    DiaChiService diaChiService;

    @PostMapping("/add")
    public ApiResponse<Boolean> createDiaChi(@RequestBody DiaChiReq diaChiReq) {
        Boolean result = true;
        diaChiReq.setNgayTao(LocalDate.now());
            diaChiService.createDiaChi(diaChiReq);
            return ApiResponse.<Boolean>builder().result(result).build();
    }
}