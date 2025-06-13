package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.dto.KichThuocDto;
import datn.com.example.datn.sys.domain.dto.response.ApiResponse;
import datn.com.example.datn.sys.domain.service.KichThuocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kich-thuoc")
@RequiredArgsConstructor
public class KichThuocController {

    private final KichThuocService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<KichThuocDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(), "Lấy danh sách kích thước"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KichThuocDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id), "Lấy chi tiết kích thước"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<KichThuocDto>> create(@RequestBody KichThuocDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo mới kích thước"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<KichThuocDto>> update(@PathVariable Integer id, @RequestBody KichThuocDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật kích thước"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}