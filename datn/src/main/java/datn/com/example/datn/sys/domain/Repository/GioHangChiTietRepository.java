package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes;
import datn.com.example.datn.sys.domain.Dto.Response.GioHangRes;
import datn.com.example.datn.sys.domain.Entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {
    @Query("""
    SELECT new datn.com.example.datn.sys.domain.Dto.Response.GioHangChiTietRes(
        sp.tenSanPham,
        spct.id,
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
    JOIN spct.idKichThuoc kt
    JOIN spct.idMauSac ms
    WHERE kh.idNguoiDung.id = :idNguoiDung
""")
    List<GioHangChiTietRes> findGioHangChiTietByIdNguoiDung(@Param("idNguoiDung") Integer idNguoiDung);

    List<GioHangChiTiet> findGioHangChiTietsByIdGioHang_Id(Integer idGiohang);

    @Modifying
    @Query("""
    update GioHangChiTiet ghct 
    set ghct.soLuong = :soLuong + ghct.soLuong, ghct.ngaySua = :ngaySua 
    where ghct.idSanPhamChiTiet.id = :idSanPhamChiTiet
""")
    int updateGioHangChiTiet(@Param("soLuong") Integer soLuong,
                             @Param("ngaySua") LocalDate ngaySua,
                             @Param("idSanPhamChiTiet") Integer idSanPhamChiTiet);

}