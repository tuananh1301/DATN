package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.dto.KichThuocDto;
import datn.com.example.datn.sys.domain.entity.KichThuoc;
import datn.com.example.datn.sys.domain.mapper.KichThuocMapper;
import datn.com.example.datn.sys.domain.repository.KichThuocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KichThuocService {

    private final KichThuocRepository repository;
    private final KichThuocMapper mapper;

    public List<KichThuocDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public KichThuocDto getById(Integer id) {
        KichThuoc entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return mapper.toDto(entity);
    }

    public KichThuocDto create(KichThuocDto dto) {
        KichThuoc entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public KichThuocDto update(Integer id, KichThuocDto dto) {
        KichThuoc entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setTenKichThuoc(dto.getTenKichThuoc());
        entity.setTrangThai(dto.getTrangThai());

        return mapper.toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        KichThuoc entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        repository.delete(entity);
    }
}
