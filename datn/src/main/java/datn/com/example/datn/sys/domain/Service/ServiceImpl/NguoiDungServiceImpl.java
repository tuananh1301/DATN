package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.common.enums.Role;
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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NguoiDungServiceImpl implements NguoiDungService {
    KhachHangRepository khachHangRepository;
    NhanVienRepository nhanVienRepository;
    NguoiDungRepository nguoiDungRepository;
    NguoiDungMapper nguoiDungMapper;
    @Override
    public NguoiDungRes createCustomer(NguoiDungReq nguoiDungReq) {
        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungReq);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.toString());
        nguoiDung.setVaiTro(roles);
        nguoiDung.setNgayTao(Instant.now());
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
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nguoiDungMapper.toNguoiDungDto(nguoiDung);
    }

    @Override
    public NguoiDungRes createStaff(NguoiDungReq nguoiDungReq) {
        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungReq);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.STAFF.toString());
        nguoiDung.setVaiTro(roles);
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
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nguoiDungMapper.toNguoiDungDto(nguoiDung);
    }
}
