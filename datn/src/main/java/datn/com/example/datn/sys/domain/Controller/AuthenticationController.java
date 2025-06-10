package datn.com.example.datn.sys.domain.Controller;

import com.nimbusds.jose.JOSEException;
import datn.com.example.datn.sys.domain.Dto.Request.AuthenticationReq;
import datn.com.example.datn.sys.domain.Dto.Request.IntrospectReq;
import datn.com.example.datn.sys.domain.Dto.Request.LogoutReq;
import datn.com.example.datn.sys.domain.Dto.Request.RefreshReq;
import datn.com.example.datn.sys.domain.Dto.Response.ApiResponse;
import datn.com.example.datn.sys.domain.Dto.Response.AuthenticationRes;
import datn.com.example.datn.sys.domain.Dto.Response.IntrospectRes;
import datn.com.example.datn.sys.domain.Service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/outbound/authentication")
    ApiResponse<AuthenticationRes> outboundAuthenticate(
            @RequestParam("code") String code
    ){
        var result = authenticationService.outboundAuthentication(code);
        return ApiResponse.<AuthenticationRes>builder().result(result).build();
    }
    @PostMapping("/login")
    ApiResponse<AuthenticationRes> login(@RequestBody AuthenticationReq nguoiDung) {
        var result = authenticationService.authenticate(nguoiDung);
        return ApiResponse.<AuthenticationRes>builder().result(result).build();
    }
    @PostMapping("/refresh")
    ApiResponse<AuthenticationRes> refresh(@RequestBody RefreshReq refreshReq) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(refreshReq);
        return ApiResponse.<AuthenticationRes>builder().result(result).build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectRes> introspect(@RequestBody IntrospectReq introspectReq) throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectReq);
        return ApiResponse.<IntrospectRes>builder().result(result).build();
    }
    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutReq logoutReq) throws ParseException, JOSEException {
        authenticationService.logout(logoutReq);
        return ApiResponse.<Void>builder().build();
    }
}
