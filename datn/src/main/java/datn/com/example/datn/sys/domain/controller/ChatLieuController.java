package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.entity.ChatLieu;
import datn.com.example.datn.sys.domain.service.ChatLieuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-lieu")
public class ChatLieuController {

    private final ChatLieuService service;

    public ChatLieuController(ChatLieuService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ChatLieu>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatLieu> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ChatLieu> create(@RequestBody ChatLieu chatLieu) {
        return ResponseEntity.ok(service.create(chatLieu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatLieu> update(@PathVariable Integer id, @RequestBody ChatLieu chatLieu) {
        return ResponseEntity.ok(service.update(id, chatLieu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Xóa thành công");
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy chất liệu để xóa");
        }
    }
}
