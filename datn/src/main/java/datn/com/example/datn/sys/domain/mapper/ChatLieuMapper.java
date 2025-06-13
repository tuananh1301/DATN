package datn.com.example.datn.sys.domain.mapper;

import datn.com.example.datn.sys.domain.dto.ChatLieuDto;
import datn.com.example.datn.sys.domain.entity.ChatLieu;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatLieuMapper {
    ChatLieuDto toDto(ChatLieu entity);
    ChatLieu toEntity(ChatLieuDto dto);
    List<ChatLieuDto> toDtoList(List<ChatLieu> list);
}