package datn.com.example.datn.sys.domain.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import datn.com.example.datn.sys.domain.Dto.Request.AuthenticationReq;
import datn.com.example.datn.sys.domain.Dto.Request.IntrospectReq;
import datn.com.example.datn.sys.domain.Dto.Request.LogoutReq;
import datn.com.example.datn.sys.domain.Dto.Request.RefreshReq;
import datn.com.example.datn.sys.domain.Dto.Response.AuthenticationRes;

import datn.com.example.datn.sys.domain.Dto.Response.IntrospectRes;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;

import java.text.ParseException;

public interface AuthenticationService {
    String generateToken(NguoiDung nguoiDung);
    String generateRefreshToken(NguoiDung nguoiDung);
    AuthenticationRes authenticate(AuthenticationReq req);
    SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException;
    String buildScope(NguoiDung nguoiDung);
    AuthenticationRes refreshToken(RefreshReq request) throws ParseException, JOSEException;
    void logout(LogoutReq request) throws ParseException, JOSEException;
    IntrospectRes introspect(IntrospectReq request) throws ParseException, JOSEException;
    AuthenticationRes outboundAuthentication(String code);

}
