package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietReq;

public interface GioHangChiTietService {
    Boolean addGioHangChiTiet(GioHangChiTietReq req);
    Boolean updateGioHangChiTiet(Integer idGioHang, Integer idGioHangChiTiet, SanPhamChiTietReq req);
    void deleteGioHangChiTiet(Integer idGioHangChiTiet);
}
