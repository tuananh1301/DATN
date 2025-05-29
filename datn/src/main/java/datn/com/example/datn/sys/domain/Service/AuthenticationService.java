package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Authentication;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;

public interface AuthenticationService {
    String generateToken(NguoiDung nguoiDung);
    String generateRefreshToken(NguoiDung nguoiDung);
    Authentication authenticate(NguoiDung nguoiDung);
}
