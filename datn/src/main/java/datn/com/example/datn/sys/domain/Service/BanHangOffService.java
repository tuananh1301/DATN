package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;

import java.util.List;
import java.util.Optional;

public interface BanHangOffService {
    SanPhamChiTietRes findAllSanPhamChiTietsByMaSanPham(SanPhamChiTietSellOffReq sanPhamChiTietSellOffReq);
}
