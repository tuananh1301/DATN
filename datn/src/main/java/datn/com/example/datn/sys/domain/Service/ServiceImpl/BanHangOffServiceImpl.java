package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.Dto.Request.CheckOutReq;
import datn.com.example.datn.sys.domain.Dto.Request.HoaDonChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.HoaDonReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonChiTietRes;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonRes;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Entity.*;
import datn.com.example.datn.sys.domain.Mapper.HoaDonChiTietMapper;
import datn.com.example.datn.sys.domain.Mapper.HoaDonMapper;
import datn.com.example.datn.sys.domain.Mapper.SanPhamChiTietMapper;
import datn.com.example.datn.sys.domain.Repository.*;
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
    KhachHangRepository khachHangRepository;
    ThanhToanRepository thanhToanRepository;
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
        hoaDon.setIdNhanVien(nv);
        hoaDon.setNgayTao(LocalDate.now());
        String maHoaDon = RandomStringUtils.randomAlphanumeric(8);
        hoaDon.setMaHoaDon(maHoaDon);
        return hoaDonMapper.toHoaDonRes(hoaDonRepository.save(hoaDon));
    }

    @Override
    public HoaDonChiTietRes createHoaDonChiTiet(HoaDonChiTietReq hoaDonChiTietReq) {
        HoaDonChiTiet hoaDonChiTietMapper1 = hoaDonChiTietMapper.toHoaDonChiTiet(hoaDonChiTietReq);
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonChiTietReq.getIdHoaDon())
                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));

        HoaDonChiTiet checkHoaDonChiTiet = hoaDonChiTietRepository
                .findByIdSanPhamChiTietAndIdHoaDon_Id(hoaDonChiTietMapper1.getIdSanPhamChiTiet(), hoaDonChiTietReq.getIdHoaDon());

        HoaDonChiTiet hoaDonChiTiet;

        if (checkHoaDonChiTiet != null) {
            checkHoaDonChiTiet.setSoLuong(checkHoaDonChiTiet.getSoLuong() + 1);
            checkHoaDonChiTiet.setNgayTao(LocalDate.now());
            hoaDonChiTiet = hoaDonChiTietRepository.save(checkHoaDonChiTiet);
        } else {
            HoaDonChiTietRes hoaDonChiTietRes = new HoaDonChiTietRes();
            hoaDonChiTietRes.setIdSanPhamChiTiet(hoaDonChiTietReq.getIdSanPhamChiTiet());
            hoaDonChiTietRes.setIdHoaDon(hoaDon.getId());
            hoaDonChiTietRes.setNgayTao(LocalDate.now());
            hoaDonChiTietRes.setTrangThai(true);
            hoaDonChiTietRes.setDonGia(hoaDonChiTietReq.getDonGia());
            hoaDonChiTietRes.setSoLuong(1);
            hoaDonChiTiet = hoaDonChiTietMapper.toHoaDonCT(hoaDonChiTietRes);
            hoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);
        }

        return hoaDonChiTietMapper.toHoaDonChiTietRes(hoaDonChiTiet);
    }


    @Override
    public void checkOut(CheckOutReq checkOutReq) {
        HoaDon hoaDon = hoaDonRepository.findById(checkOutReq.getIdHoaDon())
                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));

        hoaDon.setNgaySua(LocalDate.now());
        hoaDon.setTongTien(checkOutReq.getTongTien());
        hoaDon.setNgayThanhToan(LocalDate.now());

        KhachHang khachHang = khachHangRepository.findById(checkOutReq.getIdKhachHang())
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        hoaDon.setIdKhachHang(khachHang);

        hoaDonRepository.save(hoaDon);

        ThanhToan thanhToan = new ThanhToan();
        thanhToan.setTenHinhThucThanhToan(checkOutReq.getTenHinhThucThanhToan());
        thanhToan.setIdHoaDon(hoaDon);
        thanhToan.setNgayTao(LocalDate.now());
        thanhToan.setSoTien(checkOutReq.getTongTien());
        thanhToan.setNgayThanhToan(LocalDate.now());

        thanhToanRepository.save(thanhToan);
    }
}
