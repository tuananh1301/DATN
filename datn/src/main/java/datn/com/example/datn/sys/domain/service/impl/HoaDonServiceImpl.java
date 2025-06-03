package datn.com.example.datn.sys.domain.service.impl;

import datn.com.example.datn.sys.domain.entity.HoaDon;
import datn.com.example.datn.sys.domain.repository.HoaDonRepository;
import datn.com.example.datn.sys.domain.service.HoaDonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepository repository;

    public HoaDonServiceImpl(HoaDonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HoaDon> getAll() {
        return repository.findAll();
    }

    @Override
    public HoaDon getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public HoaDon create(HoaDon hoaDon) {
        return repository.save(hoaDon);
    }

    @Override
    public HoaDon update(Integer id, HoaDon hoaDon) {
        Optional<HoaDon> optional = repository.findById(id);
        if (optional.isPresent()) {
            hoaDon.setId(id);
            return repository.save(hoaDon);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<HoaDon> hoaDonOpt = repository.findById(id);
        if (hoaDonOpt.isPresent()) {
            HoaDon hoaDon = hoaDonOpt.get();
            // Chỉ xóa nếu trạng thái là false (pending)
            if (Boolean.FALSE.equals(hoaDon.getTrangThai())) {
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
