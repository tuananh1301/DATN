package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.dto.SanPhamChiTietDto;
import datn.com.example.datn.sys.domain.dto.response.ApiResponse;
import datn.com.example.datn.sys.domain.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham-chi-tiet")
@RequiredArgsConstructor
public class SanPhamChiTietController {

    private final SanPhamChiTietService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SanPhamChiTietDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(), "Lấy danh sách sản phẩm chi tiết"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SanPhamChiTietDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id), "Lấy sản phẩm chi tiết"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SanPhamChiTietDto>> create(@RequestBody SanPhamChiTietDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo mới sản phẩm chi tiết"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SanPhamChiTietDto>> update(@PathVariable Integer id,
                                                                 @RequestBody SanPhamChiTietDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật sản phẩm chi tiết"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}
