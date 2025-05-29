package datn.com.example.datn.sys.domain.Controller;

import datn.com.example.datn.sys.domain.Dto.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Authentication;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import datn.com.example.datn.sys.domain.Service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<Authentication> login(@RequestBody NguoiDung nguoiDung) {
        var result = authenticationService.authenticate(nguoiDung);
        return ApiResponse.<Authentication>builder().result(result).build();
    }
}
