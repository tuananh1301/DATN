package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes;

import java.util.List;

public interface GioHangChiTietService {
    Boolean addGioHangChiTiet(GioHangChiTietReq req);
    Boolean updateGioHangChiTiet(GioHangChiTietReq req);
}
