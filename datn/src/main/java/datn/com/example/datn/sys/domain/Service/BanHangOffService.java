package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Request.HoaDonChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.HoaDonReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonChiTietRes;
import datn.com.example.datn.sys.domain.Dto.Response.HoaDonRes;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;

public interface BanHangOffService {
    SanPhamChiTietRes findAllSanPhamChiTietsByMaSanPham(SanPhamChiTietSellOffReq sanPhamChiTietSellOffReq);
    HoaDonRes createHoaDon(HoaDonReq hoaDonReq);
    HoaDonChiTietRes createHoaDonChiTiet(HoaDonChiTietReq hoaDonChiTietReq);
}
