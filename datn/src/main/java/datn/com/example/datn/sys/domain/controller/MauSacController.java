package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.dto.MauSacDto;
import datn.com.example.datn.sys.domain.dto.response.ApiResponse;
import datn.com.example.datn.sys.domain.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mau-sac")
@RequiredArgsConstructor
public class MauSacController {

    private final MauSacService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MauSacDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(), "Lấy danh sách màu sắc"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MauSacDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id), "Lấy chi tiết màu sắc"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MauSacDto>> create(@RequestBody MauSacDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo mới màu sắc"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MauSacDto>> update(@PathVariable Integer id, @RequestBody MauSacDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật màu sắc"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}
