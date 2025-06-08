package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes;
import datn.com.example.datn.sys.domain.Entity.GioHang;
import datn.com.example.datn.sys.domain.Entity.GioHangChiTiet;
import datn.com.example.datn.sys.domain.Entity.KhachHang;
import datn.com.example.datn.sys.domain.Mapper.GioHangChiTietMapper;
import datn.com.example.datn.sys.domain.Repository.GioHangChiTietRepository;
import datn.com.example.datn.sys.domain.Repository.GioHangRepository;
import datn.com.example.datn.sys.domain.Repository.KhachHangRepository;
import datn.com.example.datn.sys.domain.Repository.SanPhamChiTietRepository;
import datn.com.example.datn.sys.domain.Service.GioHangChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    GioHangRepository gioHangRepository;

    KhachHangRepository khachHangRepository;

    GioHangChiTietRepository gioHangChiTietRepository;

    SanPhamChiTietRepository sanPhamChiTietRepository;

    GioHangChiTietMapper gioHangChiTietMapper;

    @Override
    public GioHangChiTietRes addGioHangChiTiet(GioHangChiTietReq req) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietMapper.toEntity(req);
        var sanPhamChiTiet = sanPhamChiTietRepository.findSanPhamChiTietByKichThuoAndMauSac(req.getIdKichThuoc(), req.getIdMauSac(), req.getIdSanPham())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Integer idNguoiDung = ((Long) jwt.getClaim("idNguoiDung")).intValue();
        System.out.println(idNguoiDung);
        KhachHang khachHang = khachHangRepository.findByIdNguoiDung_Id(idNguoiDung);
        GioHang gioHang = gioHangRepository.findByIdKhachHang_Id(khachHang.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        gioHangChiTiet.setNgayTao(LocalDate.now());
        gioHangChiTiet.setIdGioHang(gioHang);
        gioHangChiTiet.setIdSanPhamChiTiet(sanPhamChiTiet);
        return gioHangChiTietMapper.toResponse(gioHangChiTietRepository.save(gioHangChiTiet));
    }
}
