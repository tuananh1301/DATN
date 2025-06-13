package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.dto.MauSacDto;
import datn.com.example.datn.sys.domain.entity.MauSac;
import datn.com.example.datn.sys.domain.mapper.MauSacMapper;
import datn.com.example.datn.sys.domain.repository.MauSacRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MauSacService {

    private final MauSacRepository repository;
    private final MauSacMapper mapper;

    public List<MauSacDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public MauSacDto getById(Integer id) {
        MauSac entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return mapper.toDto(entity);
    }

    public MauSacDto create(MauSacDto dto) {
        MauSac entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public MauSacDto update(Integer id, MauSacDto dto) {
        MauSac entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setTenMauSac(dto.getTenMauSac());
        entity.setMaHex(dto.getMaHex());
        entity.setTrangThai(dto.getTrangThai());

        return mapper.toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        MauSac entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        repository.delete(entity);
    }
}