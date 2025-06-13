package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.dto.HoaDonDto;
import datn.com.example.datn.sys.domain.entity.HoaDon;
import datn.com.example.datn.sys.domain.mapper.HoaDonMapper;
import datn.com.example.datn.sys.domain.repository.HoaDonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HoaDonService {

    private final HoaDonRepository repository;
    private final HoaDonMapper mapper;

    public List<HoaDonDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public HoaDonDto getById(Integer id) {
        HoaDon hoaDon = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return mapper.toDto(hoaDon);
    }

    public HoaDonDto create(HoaDonDto dto) {
        HoaDon entity = mapper.toEntity(dto);

        entity.setTrangThai("completed".equalsIgnoreCase(dto.getTrangThai()));
        return mapper.toDto(repository.save(entity));
    }

    public HoaDonDto update(Integer id, HoaDonDto dto) {
        HoaDon entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setTrangThai("completed".equalsIgnoreCase(dto.getTrangThai()));
        return mapper.toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        HoaDon hoaDon = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));


        if (Boolean.TRUE.equals(hoaDon.getTrangThai())) {
            throw new AppException(ErrorCode.DELETE_INVALID_STATUS);
        }

        repository.deleteById(id);
    }
}
