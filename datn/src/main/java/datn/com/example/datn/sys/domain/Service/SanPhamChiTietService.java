package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.HienThiSanPhamDto;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;

import java.util.List;

public interface SanPhamChiTietService {
    List<HienThiSanPhamDto> homeSanPham();
    List<HienThiSanPhamDto> getSanPhamByIdDanhMuc(Integer idDanhMuc);
}
