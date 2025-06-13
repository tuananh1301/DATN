package datn.com.example.datn.sys.domain.repository;

import datn.com.example.datn.sys.domain.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
}
