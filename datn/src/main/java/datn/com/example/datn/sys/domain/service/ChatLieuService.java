package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.sys.domain.entity.ChatLieu;

import java.util.List;

public interface ChatLieuService {
    List<ChatLieu> getAll();
    ChatLieu getById(Integer id);
    ChatLieu create(ChatLieu chatLieu);
    ChatLieu update(Integer id, ChatLieu chatLieu);
    boolean delete(Integer id);
}
