package datn.com.example.datn.sys.domain.service.impl;

import datn.com.example.datn.sys.domain.entity.SanPhamChiTiet;
import datn.com.example.datn.sys.domain.repository.SanPhamChiTietRepository;
import datn.com.example.datn.sys.domain.service.SanPhamChiTietService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    private final SanPhamChiTietRepository repository;

    public SanPhamChiTietServiceImpl(SanPhamChiTietRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SanPhamChiTiet> getAll() {
        return repository.findAll();
    }

    @Override
    public SanPhamChiTiet getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SanPhamChiTiet create(SanPhamChiTiet spct) {
        return repository.save(spct);
    }

    @Override
    public SanPhamChiTiet update(Integer id, SanPhamChiTiet spct) {
        if (!repository.existsById(id)) return null;
        spct.setId(id);
        return repository.save(spct);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
