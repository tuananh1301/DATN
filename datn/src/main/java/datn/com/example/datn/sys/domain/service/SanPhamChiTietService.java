package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.dto.SanPhamChiTietDto;
import datn.com.example.datn.sys.domain.entity.SanPhamChiTiet;
import datn.com.example.datn.sys.domain.mapper.SanPhamChiTietMapper;
import datn.com.example.datn.sys.domain.repository.SanPhamChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamChiTietService {

    private final SanPhamChiTietRepository repository;
    private final SanPhamChiTietMapper mapper;

    public List<SanPhamChiTietDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public SanPhamChiTietDto getById(Integer id) {
        SanPhamChiTiet chiTiet = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return mapper.toDto(chiTiet);
    }

    public SanPhamChiTietDto create(SanPhamChiTietDto dto) {
        SanPhamChiTiet entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public SanPhamChiTietDto update(Integer id, SanPhamChiTietDto dto) {
        SanPhamChiTiet entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setSoLuong(dto.getSoLuong());
        entity.setGiaBan(dto.getGiaBan());
        // Nếu cần, set lại quan hệ SanPham, KichThuoc, MauSac ở đây

        return mapper.toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
