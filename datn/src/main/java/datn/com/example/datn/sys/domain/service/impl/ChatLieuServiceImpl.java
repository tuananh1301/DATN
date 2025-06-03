package datn.com.example.datn.sys.domain.service.impl;

import datn.com.example.datn.sys.domain.entity.ChatLieu;
import datn.com.example.datn.sys.domain.repository.ChatLieuRepository;
import datn.com.example.datn.sys.domain.service.ChatLieuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {

    private final ChatLieuRepository repository;

    public ChatLieuServiceImpl(ChatLieuRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ChatLieu> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatLieu getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ChatLieu create(ChatLieu chatLieu) {
        return repository.save(chatLieu);
    }

    @Override
    public ChatLieu update(Integer id, ChatLieu chatLieu) {
        Optional<ChatLieu> optional = repository.findById(id);
        if (optional.isPresent()) {
            chatLieu.setId(id);
            return repository.save(chatLieu);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
