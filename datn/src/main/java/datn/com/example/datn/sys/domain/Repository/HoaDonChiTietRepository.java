package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.HoaDonChiTiet;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {
    HoaDonChiTiet findByIdSanPhamChiTietAndIdHoaDon_Id(SanPhamChiTiet idSanPhamChiTiet, Integer idHoaDonId);
}
