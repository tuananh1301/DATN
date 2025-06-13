package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Dto.Response.HienThiSanPhamRes;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


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
                    JOIN DanhMuc dm ON sp.id = sp.danhMuc.id\s
                    JOIN HinhAnh ha on ha.idSanPhamChiTiet.id = spct.id
                    where ha.laHinhChinh = true and dm.id = :idDanhMuc
                    GROUP BY sp.tenSanPham, ha.duongDanHinhAnh \s
                    order by sp.id desc
        """)
    List<HienThiSanPhamRes> sanPhamByIdDanhMuc(@Param("idDanhMuc") Integer idDanhMuc);

    @Query("""
        select spct from SanPhamChiTiet spct where spct.idKichThuoc.id = :idKichThuoc 
                and spct.idMauSac.id = :idMauSac and spct.idSanPham.id = :idSanPham
                and spct.idMauSac.id = :idMauSac and spct.idSanPham.id = :idSanPham
        """)
    Optional<SanPhamChiTiet> findSanPhamChiTietByKichThuoAndMauSac(@Param("idKichThuoc") Integer idKichThuoc,
                                                                  @Param("idMauSac") Integer idMauSac,
                                                                   @Param("idSanPham") Integer idSanPham);
    @Query("""
        select spct from SanPhamChiTiet spct where spct.idKichThuoc.id = :idKichThuoc 
                and spct.idMauSac.id = :idMauSac and spct.idSanPham.maSanPham = :maSanPham
        """)
    SanPhamChiTiet findSanPhamChiTietsByIdSanPham_MaSanPham(@Param("idKichThuoc") Integer idKichThuoc,
                                                                  @Param("idMauSac") Integer idMauSac,
                                                                  @Param("maSanPham") String maSanPham);

    List<SanPhamChiTiet> findSanPhamChiTietsByIdSanPham_Id(Integer idSanPham);
}
