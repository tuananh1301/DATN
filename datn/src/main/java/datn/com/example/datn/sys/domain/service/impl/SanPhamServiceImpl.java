package datn.com.example.datn.sys.domain.service.impl;

import datn.com.example.datn.sys.domain.entity.SanPham;
import datn.com.example.datn.sys.domain.repository.SanPhamRepository;
import datn.com.example.datn.sys.domain.service.SanPhamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    private final SanPhamRepository repository;

    public SanPhamServiceImpl(SanPhamRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SanPham> getAll() {
        return repository.findAll();
    }

    @Override
    public SanPham getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SanPham create(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public SanPham update(Integer id, SanPham sanPham) {
        Optional<SanPham> optional = repository.findById(id);
        if (optional.isPresent()) {
            sanPham.setId(id);
            return repository.save(sanPham);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
