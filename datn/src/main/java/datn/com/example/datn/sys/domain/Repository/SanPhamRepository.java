package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
}
