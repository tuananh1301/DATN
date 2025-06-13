package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungReq;
import datn.com.example.datn.sys.domain.Dto.Response.NguoiDungRes;
import datn.com.example.datn.sys.domain.Entity.KhachHang;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import datn.com.example.datn.sys.domain.Entity.NhanVien;
import datn.com.example.datn.sys.domain.Mapper.NguoiDungMapper;
import datn.com.example.datn.sys.domain.Repository.KhachHangRepository;
import datn.com.example.datn.sys.domain.Repository.NguoiDungRepository;
import datn.com.example.datn.sys.domain.Repository.NhanVienRepository;
import datn.com.example.datn.sys.domain.Service.NguoiDungService;
import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungUpdateRequest;
import datn.com.example.datn.sys.domain.Entity.*;
import datn.com.example.datn.sys.domain.Repository.*;
import datn.com.example.datn.sys.domain.constant.PredefinedRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NguoiDungServiceImpl implements NguoiDungService {
    KhachHangRepository khachHangRepository;
    NhanVienRepository nhanVienRepository;
    NguoiDungRepository nguoiDungRepository;
    NguoiDungMapper nguoiDungMapper;
    VaiTroRepository vaiTroRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public NguoiDungRes createCustomer(NguoiDungReq nguoiDungReq) {
        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungReq);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
        HashSet<VaiTro> vaiTro1 = new HashSet<>();
        vaiTroRepository.findById(PredefinedRole.CUSTOMER_ROLE).ifPresent(vaiTro1::add);
        nguoiDung.setVaiTro(vaiTro1);
        nguoiDung.setNgayTao(LocalDate.now());
        try {
            nguoiDungRepository.save(nguoiDung);
            KhachHang khachHang = new KhachHang();
            var nguoiDung1 = nguoiDungRepository.findByTenDangNhap(nguoiDung.getTenDangNhap()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
            khachHang.setIdNguoiDung(nguoiDung1);
            khachHang.setTenKhachHang(nguoiDung.getFullName());
            khachHang.setNgaySinh(nguoiDung.getNgaySinh());
            khachHang.setGioiTinh(nguoiDung.getGioiTinh());
            khachHang.setNgayTao(nguoiDung.getNgayTao());
            khachHangRepository.save(khachHang);
        }
        catch (AppException e) {
            throw new AppException(ErrorCode.USER_NOT_EXISTS);
        }
        return nguoiDungMapper.toNguoiDungDto(nguoiDung);
    }

    @Override
    public NguoiDungRes createStaff(NguoiDungReq nguoiDungReq) {
        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungReq);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
        HashSet<VaiTro> vaiTro1 = new HashSet<>();
        vaiTroRepository.findById(PredefinedRole.STAFF_ROLE).ifPresent(vaiTro1::add);
        nguoiDung.setVaiTro(vaiTro1);
        try {
            nguoiDungRepository.save(nguoiDung);
            NhanVien nhanVien = new NhanVien();
            var nguoiDung1 = nguoiDungRepository.findByTenDangNhap(nguoiDung.getTenDangNhap()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
            nhanVien.setIdNguoiDung(nguoiDung1);
            nhanVien.setTenNhanVien(nguoiDung.getFullName());
            nhanVien.setNgaySinh(nguoiDung.getNgaySinh());
            nhanVien.setGioiTinh(nguoiDung.getGioiTinh());
            nhanVienRepository.save(nhanVien);
        }
        catch (AppException e) {
            throw new AppException(ErrorCode.USER_NOT_EXISTS);
        }
        return nguoiDungMapper.toNguoiDungDto(nguoiDung);
    }

    @Override
    public NguoiDungRes updateCustomer(Integer id, NguoiDungUpdateRequest nguoiDungUpdateRequest) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTS));
        nguoiDungMapper.toUpdateNguoiDung(nguoiDung, nguoiDungUpdateRequest);
        nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
        var vaiTro = vaiTroRepository.findAllById(nguoiDungUpdateRequest.getVaiTro());
        nguoiDung.setVaiTro(new HashSet<>(vaiTro));
        return nguoiDungMapper.toNguoiDungDto(nguoiDungRepository.save(nguoiDung));
    }
}
