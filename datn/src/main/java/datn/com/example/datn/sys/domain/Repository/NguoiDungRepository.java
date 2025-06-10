package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
}
