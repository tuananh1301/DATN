package datn.com.example.datn.sys.domain.repository;

import datn.com.example.datn.sys.domain.entity.KichThuoc;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface KichThuocRepository extends JpaRepository<KichThuoc, Integer> {
}
