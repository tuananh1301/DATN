package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    KhachHang findByIdNguoiDung_Id(Integer idNguoiDung);
}
