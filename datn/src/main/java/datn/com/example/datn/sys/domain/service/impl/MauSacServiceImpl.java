package datn.com.example.datn.sys.domain.service.impl;

import datn.com.example.datn.sys.domain.entity.MauSac;
import datn.com.example.datn.sys.domain.repository.MauSacRepository;
import datn.com.example.datn.sys.domain.service.MauSacService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {
    private final MauSacRepository repository;

    public MauSacServiceImpl(MauSacRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MauSac> getAll() {
        return repository.findAll();
    }

    @Override
    public MauSac getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MauSac create(MauSac mauSac) {
        return repository.save(mauSac);
    }

    @Override
    public MauSac update(Integer id, MauSac mauSac) {
        Optional<MauSac> optional = repository.findById(id);
        if (optional.isPresent()) {
            mauSac.setId(id);
            return repository.save(mauSac);
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
