package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Response.HienThiSanPhamRes;

import java.util.List;

public interface SanPhamChiTietService {
    List<HienThiSanPhamRes> homeSanPham();
    List<HienThiSanPhamRes> getSanPhamByIdDanhMuc(Integer idDanhMuc);
    List<HienThiSanPhamRes> getSanPhamByIdSanPham(Integer idSanPham);
    List<HienThiSanPhamRes> themGioHan(Integer idSanPham);
}
