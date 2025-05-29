package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.GioHangChiTiet;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {

}
