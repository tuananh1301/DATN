package datn.com.example.datn.sys.domain.Repository;

import datn.com.example.datn.sys.domain.Entity.InvalidatedToken;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
