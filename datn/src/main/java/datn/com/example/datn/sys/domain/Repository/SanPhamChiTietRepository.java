package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Dto.HienThiSanPhamDto;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    @Query("SELECT new datn.com.example.datn.sys.domain.Dto.HienThiSanPhamDto( \n" +
            "    sp.id,\n" +
            "    sp.tenSanPham,\n" +
            "    MIN(spct.giaBan) AS giaBan,\n" +
            "    ha.duongDanHinhAnh )\n" +
            "FROM SanPhamChiTiet spct\n" +
            "JOIN SanPham sp ON sp.id = spct.idSanPham.id \n" +
            "JOIN HinhAnh ha on ha.idSanPhamChiTiet.id = spct.id\n" +
            "where ha.laHinhChinh = true\n" +
            "GROUP BY sp.tenSanPham, ha.duongDanHinhAnh")
    public List<HienThiSanPhamDto> sanPham();

    @Query("SELECT new datn.com.example.datn.sys.domain.Dto.HienThiSanPhamDto( \n" +
            "    sp.id,\n" +
            "    sp.tenSanPham,\n" +
            "    MIN(spct.giaBan) AS giaBan,\n" +
            "    ha.duongDanHinhAnh )\n" +
            "FROM SanPhamChiTiet spct\n" +
            "JOIN SanPham sp ON sp.id = spct.idSanPham.id \n" +
            "JOIN DanhMuc dm ON sp.id = sp.idDanhMuc.id \n" +
            "JOIN HinhAnh ha on ha.idSanPhamChiTiet.id = spct.id\n" +
            "where ha.laHinhChinh = true and dm.id = :idDanhMuc\n" +
            "GROUP BY sp.tenSanPham, ha.duongDanHinhAnh")
    public List<HienThiSanPhamDto> sanPhamByIdDanhMuc(@Param("idDanhMuc") Integer idDanhMuc);
}
