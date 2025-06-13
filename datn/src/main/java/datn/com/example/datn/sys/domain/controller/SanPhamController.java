package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.dto.SanPhamDto;
import datn.com.example.datn.sys.domain.service.SanPhamService;
import datn.com.example.datn.sys.domain.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@RequiredArgsConstructor
public class SanPhamController {

    private final SanPhamService sanPhamService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SanPhamDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(sanPhamService.getAll(), "Lấy tất cả sản phẩm"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SanPhamDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(sanPhamService.getById(id), "Lấy chi tiết sản phẩm"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SanPhamDto>> create(@RequestBody SanPhamDto dto) {
        return ResponseEntity.ok(ApiResponse.success(sanPhamService.create(dto), "Thêm mới sản phẩm"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SanPhamDto>> update(@PathVariable Long id, @RequestBody SanPhamDto dto) {
        return ResponseEntity.ok(ApiResponse.success(sanPhamService.update(id, dto), "Cập nhật sản phẩm"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        sanPhamService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}
