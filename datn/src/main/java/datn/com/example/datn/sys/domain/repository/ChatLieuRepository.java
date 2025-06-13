package datn.com.example.datn.sys.domain.repository;

import datn.com.example.datn.sys.domain.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Integer> {
}
