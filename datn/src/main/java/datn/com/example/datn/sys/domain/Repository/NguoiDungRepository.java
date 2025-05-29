package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByUsername(String username);
}
