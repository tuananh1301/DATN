package datn.com.example.datn.sys.domain.service.impl;

import datn.com.example.datn.sys.domain.entity.KichThuoc;
import datn.com.example.datn.sys.domain.repository.KichThuocRepository;
import datn.com.example.datn.sys.domain.service.KichThuocService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KichThuocServiceImpl implements KichThuocService {
    private final KichThuocRepository repository;

    public KichThuocServiceImpl(KichThuocRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<KichThuoc> getAll() {
        return repository.findAll();
    }

    @Override
    public KichThuoc getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public KichThuoc create(KichThuoc kichThuoc) {
        return repository.save(kichThuoc);
    }

    @Override
    public KichThuoc update(Integer id, KichThuoc kichThuoc) {
        Optional<KichThuoc> optional = repository.findById(id);
        if (optional.isPresent()) {
            kichThuoc.setId(id);
            return repository.save(kichThuoc);
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
