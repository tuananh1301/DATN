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
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    @Transactional
    public Boolean addGioHangChiTiet(GioHangChiTietReq req) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietMapper.toEntity(req);
        var sanPhamChiTiet = sanPhamChiTietRepository.findSanPhamChiTietByKichThuoAndMauSac(req.getIdKichThuoc(), req.getIdMauSac(), req.getIdSanPham())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Integer idNguoiDung = ((Long) jwt.getClaim("idNguoiDung")).intValue();
        KhachHang khachHang = khachHangRepository.findByIdNguoiDung_Id(idNguoiDung);
        GioHang gioHang = gioHangRepository.findByIdKhachHang_Id(khachHang.getId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        List<GioHangChiTiet> ghct = gioHangChiTietRepository.findGioHangChiTietsByIdGioHang_Id(gioHang.getId());
        for (GioHangChiTiet ghc : ghct) {
            if (ghc.getIdSanPhamChiTiet().getId().equals(sanPhamChiTiet.getId())) {
                if (sanPhamChiTiet.getSoLuong()<req.getSoLuong() + ghc.getSoLuong()) {
                    throw new AppException(ErrorCode.OUT_OF_QUANTITY);
                }else {
                    gioHangChiTietRepository.updateGioHangChiTiet(req.getSoLuong(),LocalDate.now(),sanPhamChiTiet.getId());
                    return true;
                }
            }
        }
        gioHangChiTiet.setNgayTao(LocalDate.now());
        gioHangChiTiet.setIdGioHang(gioHang);
        gioHangChiTiet.setIdSanPhamChiTiet(sanPhamChiTiet);
        gioHangChiTietRepository.save(gioHangChiTiet);
        return true;
    }

    @Override
    public Boolean updateGioHangChiTiet(GioHangChiTietReq req) {

        return null;
    }


}
