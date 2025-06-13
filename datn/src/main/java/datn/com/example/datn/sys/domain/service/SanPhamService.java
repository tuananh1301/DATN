package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.dto.SanPhamDto;
import datn.com.example.datn.sys.domain.entity.SanPham;
import datn.com.example.datn.sys.domain.mapper.SanPhamMapper;
import datn.com.example.datn.sys.domain.repository.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;
    private final SanPhamMapper sanPhamMapper;

    public List<SanPhamDto> getAll() {
        List<SanPham> list = sanPhamRepository.findAll();
        return sanPhamMapper.toDtoList(list);
    }

    public SanPhamDto getById(Long id) {
        SanPham sanPham = sanPhamRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return sanPhamMapper.toDto(sanPham);
    }

    public SanPhamDto create(SanPhamDto dto) {
        SanPham entity = sanPhamMapper.toEntity(dto);
        return sanPhamMapper.toDto(sanPhamRepository.save(entity));
    }

    public SanPhamDto update(Long id, SanPhamDto dto) {
        SanPham existing = sanPhamRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        existing.setTenSanPham(dto.getTen());
        existing.setMoTa(dto.getMoTa());
        existing.setTrangThai(dto.getDeleted());

        return sanPhamMapper.toDto(sanPhamRepository.save(existing));
    }

    public void delete(Long id) {
        SanPham entity = sanPhamRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        sanPhamRepository.delete(entity);
    }
}
