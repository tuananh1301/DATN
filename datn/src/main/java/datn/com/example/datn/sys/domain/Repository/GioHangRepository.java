package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    Optional<GioHang> findByIdKhachHang_Id(Integer idKhachHang);

}

