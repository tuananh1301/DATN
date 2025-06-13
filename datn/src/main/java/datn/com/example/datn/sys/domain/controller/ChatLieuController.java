package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.dto.ChatLieuDto;
import datn.com.example.datn.sys.domain.dto.response.ApiResponse;
import datn.com.example.datn.sys.domain.service.ChatLieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-lieu")
@RequiredArgsConstructor
public class ChatLieuController {

    private final ChatLieuService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ChatLieuDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(), "Lấy danh sách chất liệu"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatLieuDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id), "Lấy chi tiết chất liệu"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ChatLieuDto>> create(@RequestBody ChatLieuDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo mới chất liệu"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatLieuDto>> update(@PathVariable Integer id, @RequestBody ChatLieuDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật chất liệu"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}