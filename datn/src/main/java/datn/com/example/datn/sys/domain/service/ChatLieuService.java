package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.dto.ChatLieuDto;
import datn.com.example.datn.sys.domain.entity.ChatLieu;
import datn.com.example.datn.sys.domain.mapper.ChatLieuMapper;
import datn.com.example.datn.sys.domain.repository.ChatLieuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatLieuService {

    private final ChatLieuRepository repository;
    private final ChatLieuMapper mapper;

    public List<ChatLieuDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public ChatLieuDto getById(Integer id) {
        ChatLieu chatLieu = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return mapper.toDto(chatLieu);
    }

    public ChatLieuDto create(ChatLieuDto dto) {
        ChatLieu entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public ChatLieuDto update(Integer id, ChatLieuDto dto) {
        ChatLieu entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setTenChatLieu(dto.getTenChatLieu());
        entity.setTrangThai(dto.getTrangThai());

        return mapper.toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        ChatLieu entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        repository.delete(entity);
    }
}