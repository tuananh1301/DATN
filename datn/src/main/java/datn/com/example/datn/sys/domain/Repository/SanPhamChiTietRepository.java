package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Dto.Response.HienThiSanPhamRes;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {

    @Query("""
                    SELECT new datn.com.example.datn.sys.domain.Dto.Response.HienThiSanPhamRes(\s
                        sp.id,
                        sp.tenSanPham,
                        MIN(spct.giaBan) AS giaBan,
                        ha.duongDanHinhAnh )
                    FROM SanPhamChiTiet spct
                    JOIN SanPham sp ON sp.id = spct.idSanPham.id\s
                    JOIN HinhAnh ha on ha.idSanPhamChiTiet.id = spct.id
                    GROUP BY sp.tenSanPham, ha.duongDanHinhAnh \s
                    order by sp.id desc
        """)
    List<HienThiSanPhamRes> sanPham();

    @Query("""
                    SELECT new datn.com.example.datn.sys.domain.Dto.Response.HienThiSanPhamRes(\s
                        sp.id,
                        sp.tenSanPham,
                        MIN(spct.giaBan) AS giaBan,
                        ha.duongDanHinhAnh )
                    FROM SanPhamChiTiet spct
                    JOIN SanPham sp ON sp.id = spct.idSanPham.id\s
                    JOIN DanhMuc dm ON sp.id = sp.idDanhMuc.id\s
                    JOIN HinhAnh ha on ha.idSanPhamChiTiet.id = spct.id
                    where ha.laHinhChinh = true and dm.id = :idDanhMuc
                    GROUP BY sp.tenSanPham, ha.duongDanHinhAnh \s
                    order by sp.id desc
        """)
    List<HienThiSanPhamRes> sanPhamByIdDanhMuc(@Param("idDanhMuc") Integer idDanhMuc);

}
