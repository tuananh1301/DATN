package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Request.GioHangChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangRes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GioHangChiTietService {
    GioHangChiTietRes addGioHangChiTiet(GioHangChiTietReq req);
    @Query("""
    SELECT new datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes(
        sp.tenSanPham,
        spct.giaBan,
        ghct.soLuong,
        spct.soLuong,
        spct.giaBan * ghct.soLuong,
        kt.tenKichThuoc,
        ms.tenMauSac,
        ha.duongDanHinhAnh
    )
    FROM GioHangChiTiet ghct
    JOIN ghct.idGioHang gh
    JOIN gh.idKhachHang kh
    JOIN ghct.idSanPhamChiTiet spct
    JOIN spct.sanPham sp
    JOIN HinhAnh ha on ha.idSanPhamChiTiet.id = spct.id 
    JOIN spct.kichThuoc kt
    JOIN spct.mauSac ms
    WHERE kh.idNguoiDung = :idNguoiDung
""")
    List<GioHangRes> findGioHangByIdNguoiDung(@Param("idNguoiDung") Integer idNguoiDung);
}
