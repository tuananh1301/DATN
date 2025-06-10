package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.Dto.Request.HoaDonChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.HoaDonReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonChiTietRes;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonRes;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Entity.HoaDon;
import datn.com.example.datn.sys.domain.Entity.HoaDonChiTiet;
import datn.com.example.datn.sys.domain.Entity.NhanVien;
import datn.com.example.datn.sys.domain.Mapper.HoaDonChiTietMapper;
import datn.com.example.datn.sys.domain.Mapper.HoaDonMapper;
import datn.com.example.datn.sys.domain.Mapper.SanPhamChiTietMapper;
import datn.com.example.datn.sys.domain.Repository.HoaDonChiTietRepository;
import datn.com.example.datn.sys.domain.Repository.HoaDonRepository;
import datn.com.example.datn.sys.domain.Repository.NhanVienRepository;
import datn.com.example.datn.sys.domain.Repository.SanPhamChiTietRepository;
import datn.com.example.datn.sys.domain.Service.BanHangOffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BanHangOffServiceImpl implements BanHangOffService {
    SanPhamChiTietRepository sanPhamChiTietRepository;
    SanPhamChiTietMapper sanPhamChiTietMapper;
    NhanVienRepository nhanVienRepository;
    HoaDonMapper hoaDonMapper;
    HoaDonChiTietMapper hoaDonChiTietMapper;
    HoaDonRepository hoaDonRepository;
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public SanPhamChiTietRes findAllSanPhamChiTietsByMaSanPham(SanPhamChiTietSellOffReq sanPhamChiTietReq) {
        return sanPhamChiTietMapper.toSanPhamChiTietRes(sanPhamChiTietRepository.findSanPhamChiTietsByIdSanPham_MaSanPham(sanPhamChiTietReq.getIdKichThuoc(), sanPhamChiTietReq.getIdMauSac(), sanPhamChiTietReq.getMaSanPham()));
    }

    @Override
    public HoaDonRes createHoaDon(HoaDonReq hoaDonReq) {
        HoaDon hoaDon = hoaDonMapper.toHoaDon(hoaDonReq);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Integer idNguoiDung = ((Long) jwt.getClaim("idNguoiDung")).intValue();
        NhanVien nv = nhanVienRepository.findByIdNguoiDung_Id(idNguoiDung);
        System.out.println(nv.getId());
        hoaDon.setIdNhanVien(nv);
        hoaDon.setNgayTao(LocalDate.now());
        String maHoaDon = RandomStringUtils.randomAlphanumeric(8);
        System.out.println(maHoaDon);
        hoaDon.setMaHoaDon(maHoaDon);
        return hoaDonMapper.toHoaDonRes(hoaDonRepository.save(hoaDon));
    }

    @Override
    public HoaDonChiTietRes createHoaDonChiTiet(HoaDonChiTietReq hoaDonChiTietReq) {
        HoaDonChiTietRes hoaDonChiTietRes = new HoaDonChiTietRes();
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonChiTietReq.getIdHoaDon()).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        hoaDonChiTietRes.setIdSanPhamChiTiet(hoaDonChiTietReq.getIdSanPhamChiTiet());
        hoaDonChiTietRes.setIdHoaDon(hoaDon.getId());
        hoaDonChiTietRes.setNgayTao(LocalDate.now());
        hoaDonChiTietRes.setTrangThai(true);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietMapper.toHoaDonCT(hoaDonChiTietRes);
        return hoaDonChiTietMapper.toHoaDonChiTietRes(hoaDonChiTietRepository.save(hoaDonChiTiet));
    }
}
