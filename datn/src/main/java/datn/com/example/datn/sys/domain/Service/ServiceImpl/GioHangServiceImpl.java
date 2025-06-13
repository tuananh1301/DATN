package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangRes;
import datn.com.example.datn.sys.domain.Entity.GioHang;
import datn.com.example.datn.sys.domain.Entity.KhachHang;
import datn.com.example.datn.sys.domain.Repository.GioHangChiTietRepository;
import datn.com.example.datn.sys.domain.Repository.GioHangRepository;
import datn.com.example.datn.sys.domain.Repository.KhachHangRepository;
import datn.com.example.datn.sys.domain.Service.GioHangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GioHangServiceImpl implements GioHangService {
    KhachHangRepository khachHangRepository;

    GioHangChiTietRepository gioHangChiTietRepository;

    GioHangRepository gioHangRepository;

    @Override
    public GioHangRes getCart() {
        GioHangRes gioHangRes = new GioHangRes();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Integer idNguoiDung = ((Long) jwt.getClaim("idNguoiDung")).intValue();
        KhachHang khachHang = khachHangRepository.findByIdNguoiDung_Id(idNguoiDung);
        GioHang gioHang = gioHangRepository.findByIdKhachHang_Id(khachHang.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        gioHangRes.setIdGioHang(gioHang.getId());
        gioHangRes.setGioHangChiTietRes(gioHangChiTietRepository.findGioHangChiTietByIdNguoiDung(idNguoiDung));
        return gioHangRes;
    }
}
